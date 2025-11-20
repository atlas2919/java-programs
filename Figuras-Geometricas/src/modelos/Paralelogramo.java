package modelos;

public class Paralelogramo extends FiguraGeometrica{
    private double _base;
    private double _altura;

    public Paralelogramo(String nombre, double base, double altura) {
        super(nombre);
        _base = base;
        _altura = altura;
    }

    public double getArea(){
        return _base * _altura;
    }
    
}
