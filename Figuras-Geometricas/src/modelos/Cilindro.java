package modelos;

public class Cilindro extends FiguraGeometrica{
    private double _radio;
    private double _altura;

    public Cilindro(String nombre, double radio, double altura){
        super(nombre); // superclass constructor
        _radio = radio;
        _altura = altura;
    }

    public double getArea(){
        return (2 * Math.PI *(_radio * (_altura + _radio)));
    }

}
