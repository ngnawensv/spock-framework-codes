package polygon
import spock.lang.Specification
import spock.lang.Subject


class ExempleSpec extends  Specification{
    @Subject
    Polygon theTestSubject = new Polygon(56)
    //expect
    def "should be a simple assertion"() {
        expect:
        1==1
    }
    
    //given when then
    def "should demontrated given-when-end"() {
        given:
        def polygon=new Polygon(4)

        when:
        int sides = polygon.numberOfSides

        then:
        sides == 4
    }

    //given when then facto
    def "should demontrated given-when-end facto"() {

        when:
        int sides = new Polygon(4).numberOfSides

        then:
        sides == 4
    }

    //Expecting exception
    def "should expecting exception"() {
        when:
        new Polygon(0)

        then:
        // no need for .class
        def e = thrown(TooFewSidesException)
        // no need for get
        e.numberOfSides == 0
    }

    //Expecting exception
    def "should expecting exception with variable"() {
        when:
        new Polygon(sides)

        then:
        def exception=thrown(TooFewSidesException)
        exception.numberOfSides == sides

        where:
        sides << [0, 1, 2]
    }

    //Data pipe
    def "should expect an Exception to be thrown for invalid input: #sides"() {
        when:
        new Polygon(sides)

        then:
        def exception=thrown(TooFewSidesException)
        exception.numberOfSides==sides

        where:
        sides<<[-1,0,1,2]
    }

    //Data pipe
    def "should be able to create a polygon with #sides sides"() {
        when:
        def polygon=new Polygon(sides)

        then:
        polygon.getNumberOfSides()==sides

        where:
        sides<<[3,4,5,8,14]
    }

    //Data pipe
    def "should be able to create a polygon with #sides sides facto"() {
        expect:
        new Polygon(sides).getNumberOfSides()==sides

        where:
        sides<<[3,4,5,8,14]
    }

    //Data table
    def "should use data for calaculating max"() {
        expect:
        Math.max(a,b)==max

        where:
        a|b|max
        1|3|3
        7|4|7
        0|0|0
    }

    //Data table
    def "should use data for calaculating max with syntaxic sugar"() {
        expect:
        Math.max(a,b)==max

        where:
        a|b||max
        1|3||3
        7|4||7
        0|0||0
    }

    //Mock
    def "should be able to mock concrete class"() {
        given:
        //Renderer renderer = Mock() ou
        def renderer =Mock(Renderer)
        @Subject
        def polygon= new Polygon(4,renderer)

        when:
        polygon.draw()

        then:
        renderer.drawLine()

    }

    //stub
    def "should be able to create a stub"() {
        given:
        Palette palette = Stub()
        palette.getPrimaryColor() >>"Red"
        @Subject //This annotation is only for informational purposes, but can help in making sure we understand
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour() == "Red"

    }

    def "should demonstrate helper methods"() {
        given:
        def renderer = Mock(Renderer)
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def shape = shapeFactory.createDefaultPolygon()

        then:
        checkDefaultShape(shape, renderer)
    }

    private static void checkDefaultShape(Polygon shape, Renderer renderer) {
        assert shape.numberOfSides == 4
        assert shape.renderer == renderer
    }
}

