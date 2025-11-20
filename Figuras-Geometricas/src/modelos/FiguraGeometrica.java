package modelos;

import interfaz.ICalculable;

public abstract class FiguraGeometrica implements ICalculable{
    private String _nombre;

    //constructor parametrizado
    public FiguraGeometrica(String nombre){
        _nombre = nombre;
    }

    public abstract double getArea();

    public String getName(){
        return _nombre;
    };
}
