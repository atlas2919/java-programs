package modelos;

public class Circulo extends FiguraGeometrica{
    private double _radio;
    
    public Circulo(String nombre, double radio) {
        super(nombre);
        _radio = radio;
    }

    public double getArea(){
        return Math.PI * Math.pow(_radio, 2);
    }

}
