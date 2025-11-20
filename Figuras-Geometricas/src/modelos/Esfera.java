package modelos;

public class Esfera extends FiguraGeometrica{
    private double _radio;

    public Esfera(String nombre, double radio){
        super(nombre);
        _radio = radio;
    }

    public double getArea(){
        return (4 * Math.PI * Math.pow(_radio, 2));
    }
}
