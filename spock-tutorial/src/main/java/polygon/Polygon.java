package polygon;


public class Polygon {

    private final int numberOfSides;
    private Renderer renderer;

    public Polygon(int numberOfSide) throws TooFewSidesException {
        if (numberOfSide <= 2) {
            throw new TooFewSidesException("The shape must have more than 2 sides", numberOfSide);
        }
        this.numberOfSides =numberOfSide;

    }


    public Polygon( int numberOfSide, Renderer renderer){
            this.numberOfSides =numberOfSide;
            this.renderer=renderer;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public Renderer getRenderer() {
        return renderer;
    }


    public void draw(){
        for (int i = 0; i < numberOfSides; i++) {
            renderer.drawLine();
        }
    }
}
