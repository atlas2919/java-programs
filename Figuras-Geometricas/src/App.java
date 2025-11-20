import interfaz.ICalculable;
import modelos.Calculadora;
import modelos.Cilindro;
import modelos.Circulo;
import modelos.CoronaCircular;
import modelos.Cubo;
import modelos.Esfera;
import modelos.Hexagono;
import modelos.Ortoedro;
import modelos.Paralelogramo;
import modelos.PoligonoRegular;
import modelos.Rectangulo;
import modelos.Trapecio;
import modelos.Triangulo;

public class App {
    public static void main(String[] args) throws Exception {

        var rect1 = new Rectangulo("Rectangulo", 3, 8);
        var rect2 = new Rectangulo("Cuadrado", 2, 2);
        var tri1 = new Triangulo("Triangulo", 3, 3, 3);
        var cir1 = new Circulo("Circulo", 4);
        var hex1 = new Hexagono("Hexagono", 6, 2.38);
        var par1 = new Paralelogramo("Paralelogramo", 20, 13);
        var tra1 = new Trapecio("Trapecio", 20, 10, 16);
        var cub1 = new Cubo("Cubo", 4);
        var cilindro = new Cilindro("Cilindro", 6.23, 20);
        var ortoedro = new Ortoedro("Ortoedro", 2, 3, 4);
        var poligono = new PoligonoRegular("Poligono Regular", 23.12, 8, 12.1);
        var corona = new CoronaCircular("Corona Circular", 10, 1.4);
        var esfera = new Esfera("Esfera", 26.7);

        ICalculable[] figuras = {
            rect1, 
            rect2,
            tri1,
            cir1,
            hex1, 
            par1, 
            tra1, 
            cub1, 
            cilindro, 
            ortoedro,
            poligono,
            corona,
            esfera
        };

        System.out.println("\nAreas de las Figuras Geometricas:\n");
        for (ICalculable figura : figuras) {
            System.out.println("El area del " + figura.getName() + " es: " + figura.getArea());
        }

        var calculadora = new Calculadora(figuras);
        var areaTotal = calculadora.getAreaTotal();
        var figurasMax = calculadora.getMaxFigura();
        
        System.out.println("\nEl area total de las Figuras Geometricas es: " + areaTotal);
        System.out.println("\nEl area maxima le pertenece al: " + figurasMax.getName() + ", con un area de: " + figurasMax.getArea() + "\n");


    }
}
