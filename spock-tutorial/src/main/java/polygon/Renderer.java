package polygon;

public class Renderer {

    private final Palette palette;

    public  Renderer(Palette palette){
        this.palette=palette;
    }


    public void drawLine(){
    }

    public String getForegroundColour() {
        return palette.getPrimaryColor();
    }
}
