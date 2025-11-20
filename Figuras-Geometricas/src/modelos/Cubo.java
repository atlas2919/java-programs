package modelos;

public class Cubo extends FiguraGeometrica{
    private double _lado;

    public Cubo (String nombre, double lado) {
        super(nombre);
        _lado = lado;
    }

    public double getArea(){
        return (6 * Math.pow(_lado, 2));
    }
    
}
