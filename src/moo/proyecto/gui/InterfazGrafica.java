package moo.proyecto.gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 * Esta clase implementa la interfaz gráfica de usuario que usará el programa. NO TENÉIS QUE AÑADIR
 * NI MODIFICAR CÓDIGO EN ESTA CLASE: OS VIENE DADA
 */
public class InterfazGrafica {

    /**
     * Código numérico para detectar que se ha pulsado la tecla para mover el personaje hacia
     * arriba
     */
    public static final int TECLA_ARRIBA = 0;
    /**
     * Código numérico para detectar que se ha pulsado la tecla para mover el personaje hacia abajo
     */
    public static final int TECLA_ABAJO = 1;
    /**
     * Código numérico para detectar que se ha pulsado la tecla para mover el personaje hacia la
     * izquierda
     */
    public static final int TECLA_IZQUIERDA = 2;
    /**
     * Código numérico para detectar que se ha pulsado la tecla para mover el personaje hacia la
     * derecha
     */
    public static final int TECLA_DERECHA = 3;
    /**
     * Código numérico para detectar que se ha pulsado la tecla R que comenzará de nuevo el nivel.
     */
    public static final int TECLA_R = 4;

    /**
     * Tamaño de cada columna y fila de cada escena
     */
    private static final int TILE_SIZE = 48;

    /**
     * Velocidad de animación
     */
    private static final long ANIMATION_SPEED_NS = 600000000;

    /**
     * Ancho total de la escena
     */
    private static int sceneWidth;
    /**
     * Altura total de la escena
     */
    private static int sceneHeight;

    /**
     * Mapa que mapea el nombre de un archivo con la imagen correspondiente.
     */
    private HashMap<String, ImageInfo> images = new HashMap<>();
    private boolean isLaunched = false;

    /**
     * Constructor. Crea una interfaz gráfica con el número de filas y columnas indicadas por los
     * argumento
     *
     * @param filas    el número de filas
     * @param columnas el número de columnas
     */
    public InterfazGrafica(int filas, int columnas) {
        GUIApplication.filas = filas;
        GUIApplication.columnas = columnas;
        GUIApplication.pieces = new ImageInfo[columnas][filas];
        sceneWidth = columnas * TILE_SIZE;
        sceneHeight = filas * TILE_SIZE;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mostrar();
            }
        }).start();
    }

    /**
     * Devuelve un código numérico en función de la tecla que el usuario haya pulsado, que puede
     * ser {@link #TECLA_ARRIBA}, {@link #TECLA_ABAJO}, {@link #TECLA_IZQUIERDA}, {@link #TECLA_DERECHA},
     * o {@link #TECLA_R}.
     *
     * @return el código de la tecla pulsada.
     */
    public int leeTeclaPulsada() {
        while (GUIApplication.entradaCursor.isEmpty()) {
            try {
                GUIApplication.esperaTeclas.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return GUIApplication.entradaCursor.poll();
    }

    /**
     * Coloca una cierta imagen en una posición de la matriz del recinto. La imagen está depositada
     * en un archivo cuyo nombre se intentaPasar como argumento.
     *
     * @param archivo nombre del archivo que contiene la imagen. Si es una referencia a null,
     *                no se dibujará nada o se borrará la imagen que ya haya en ese recuadro.
     * @param fila    el número de fila de la celda en la que la imagen debe colocarse
     * @param col     el número de columna de la celda en la que la imagen debe colocarse
     */
    public void colocaImagen(String archivo, int fila, int col) {
        if (fila >= 0 && fila < GUIApplication.filas && col >= 0 && col < GUIApplication.columnas) {
            if (archivo == null) {
                GUIApplication.pieces[col][fila] = null;
            } else {
                GUIApplication.pieces[col][fila] = get(archivo);
            }
        }
    }

    /**
     * Consigue la imagen almacenada en el archivo cuyo nombre se intentaPasar como argumento y añade una
     * nueva entrada en el mapa que contiene objetos con información de imágenes
     *
     * @param path el nombre del archivo
     * @return el objeto ImageInfo con información de la imagen
     */
    ImageInfo get(String path) {
        ImageInfo ii = images.get(path);
        if (ii == null) {
            ii = new ImageInfo();
            System.out.println("path = " + path);
            ii.image = new Image(getClass().getResourceAsStream(path));
            ii.frameSize = (int) ii.image.getHeight();
            ii.frames = (int) (ii.image.getWidth() / ii.frameSize);
            images.put(path, ii);
        }
        return ii;
    }

    private void mostrar() {
        if (!isLaunched) {
            isLaunched = true;
            Application.launch(GUIApplication.class);
        }
    }

    /**
     * Clase estática subclase de Application
     */
    public static class GUIApplication extends Application {

        /**
         * Cola que contiene los códigos numéricos correspondientes a las teclas pulsadas por el
         * usuario
         */
        final static Queue<Integer> entradaCursor = new ConcurrentLinkedQueue<>();
        /**
         * "Latch" para sincronizar la lectura de teclas
         */
        final static Semaphore esperaTeclas = new Semaphore(0);
        /**
         * Número de filas de la escena
         */
        static int filas;
        /**
         * Número de columnas de la escena
         */
        static int columnas;
        /**
         * Matriz de imágenes para presentar el recinto de juego
         */
        static ImageInfo[][] pieces;
        /**
         * La escena del juego
         */
        Scene gameScene;
        /**
         * Una imagen del interfaz
         */
        Canvas sceneCanvas;
        /**
         * Contenedor para la interfaz gráfica de usuario
         */
        Stage primaryStage;
        /**
         * El objeto que actualiza la imagen
         */
        private Updater timer;

        /**
         * Inicia la interfaz gráfica de usuario.
         *
         * @throws Exception lanza un objeto Exception si se produce alguna situación anómala.
         */
        @Override
        public void init() throws Exception {
            super.init(); //To change body of generated methods, choose Tools | Templates.
        }

        /**
         * Da comienzo a la construcción y presentación del interfaz gráfico de usuario. Lee
         * pulsaciones de teclas desde el teclado y las procesa, ordenando el dibujado o borrado de
         * diferentes imágenes en el GUI.
         *
         * @param primaryStage el contenedor del GUI
         * @throws Exception lanza una excepción si se produce alguna situación anómala.
         */
        @Override
        public void start(Stage primaryStage) throws Exception {
            this.primaryStage = primaryStage;
            this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.exit(0);
                }
            });
            primaryStage.setTitle("¡Snake escape!");
            Group root = new Group();
            sceneCanvas = new Canvas(sceneWidth, sceneHeight);

            root.getChildren().add(sceneCanvas);
            gameScene = new Scene(root, sceneWidth, sceneHeight, false, SceneAntialiasing.DISABLED);

            gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    boolean wasEmpty = entradaCursor.isEmpty();
                    switch (event.getCode()) {
                        case R:
                            entradaCursor.add(TECLA_R);
                            break;
                        case UP:
                        case KP_UP:
                            entradaCursor.add(TECLA_ARRIBA);
                            break;
                        case DOWN:
                        case KP_DOWN:
                            entradaCursor.add(TECLA_ABAJO);
                            break;
                        case LEFT:
                        case KP_LEFT:
                            entradaCursor.add(TECLA_IZQUIERDA);
                            break;
                        case RIGHT:
                        case KP_RIGHT:
                            entradaCursor.add(TECLA_DERECHA);
                            break;
                    }

                    if (wasEmpty && !entradaCursor.isEmpty()) {
                        esperaTeclas.release();
                    }
                }
            });
            primaryStage.setScene(gameScene);

            ResizeListener rl = new ResizeListener();
            primaryStage.widthProperty().addListener(rl);
            primaryStage.heightProperty().addListener(rl);

            resizeCanvas();

            timer = new Updater(this);
            primaryStage.show();
            timer.start();
        }

        /**
         * Permite cambiar el tamaño de la imagen
         */
        void resizeCanvas() {
            double scaleX = gameScene.getWidth() / sceneWidth;
            double scaleY = gameScene.getHeight() / sceneHeight;
            double scale = Math.min(scaleX, scaleY);
            sceneCanvas.setScaleX(scale);
            sceneCanvas.setScaleY(scale);
            sceneCanvas.setTranslateX((gameScene.getWidth() - sceneWidth) / 2);
            sceneCanvas.setTranslateY((gameScene.getHeight() - sceneHeight) / 2);
        }


        @Override
        public void stop() throws Exception {
            timer.stop();
            super.stop(); //To change body of generated methods, choose Tools | Templates.
        }

        private class ResizeListener implements ChangeListener<Number> {

            /**
             * Permite cambiar el tamaño de la ventana
             */
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                resizeCanvas();
            }
        }

        /**
         * Clase privada que permite crear un timer para actualizar las tramas del GUI.
         */
        private class Updater extends AnimationTimer {

            private GUIApplication guiApp;

            public Updater(GUIApplication guiApp) {
                this.guiApp = guiApp;
            }


            @Override
            public void handle(long l) {
                GraphicsContext gc = guiApp.sceneCanvas.getGraphicsContext2D();
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, sceneWidth, sceneHeight);

                for (int col = 0; col < columnas; col++) {
                    for (int row = 0; row < filas; row++) {
                        ImageInfo ii = pieces[col][row];
                        if (ii != null) {
                            int animationIndex = (int) ((l / ANIMATION_SPEED_NS) % ii.frames);
                            gc.drawImage(ii.image, animationIndex * ii.frameSize, 0, ii.frameSize,
                                    ii.frameSize,
                                    col * TILE_SIZE, row * TILE_SIZE, ii.frameSize,
                                    ii.frameSize);

                        }
                    }
                }
            }
        }

    }

    /**
     * Clase privada: ImageInfo. Contiene información de una cierta imagen
     */
    private class ImageInfo {

        /**
         * El objeto instancia de clase Image, que se presentará por pantalla
         */
        Image image;
        /**
         * El tamaño en altura de la trama de la imagen
         */
        int frameSize;
        /**
         * La relación entre la anchura de la imagen y su altura
         */
        int frames;
    }
}
