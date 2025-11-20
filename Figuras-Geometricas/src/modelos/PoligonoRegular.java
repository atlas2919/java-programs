package modelos;

public class PoligonoRegular extends FiguraGeometrica {
    private double _apotema;
    private int _numLados;
    private double _longitudLado;

    public PoligonoRegular(String nombre, double apotema, int numLados, double longitudLado){
        super(nombre);
        _apotema = apotema;
        _numLados = numLados;
        _longitudLado = longitudLado;
    }

    public double getArea(){
        return (_numLados * _longitudLado * _apotema) / 2;
    }
}
