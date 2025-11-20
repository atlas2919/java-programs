package modelos;

import interfaz.ICalculable;

public class Calculadora{
    private ICalculable[] _figuras;

    public Calculadora(ICalculable[] figuras) {
        _figuras = figuras;
    }

    public double getAreaTotal(){
        double areaTotal = 0;

        for (ICalculable fig : _figuras) {
            areaTotal += fig.getArea();
        }

        return areaTotal;
    }

    public ICalculable getMaxFigura() {
        ICalculable maxArea = _figuras[0];
        for (int i = 1; i < _figuras.length; i++) { // evita comparar el primer elemento consigo mismo
            if (_figuras[i].getArea() > maxArea.getArea()) {
                maxArea = _figuras[i];
            }
        }
        return maxArea;
    }

}
