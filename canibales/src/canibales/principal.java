/* Autor: Daniel Esteban Cifuentes Cossio
 * Objetivo: reconocer una estructura de datos adaptable al juego-problema Misioneros y canibales
 * Agradecimientos: Pedro Atencio Ortiz - Docente ITM 
 * 
 * Misioneros y canibales es un problema o juego (toy problem), comunmente utilizado para estudiar 
 * representación de problemas. El juego consiste en cruzar tres canibales y tres misioneros que inicialmente 
 * están de un lado del río, al otro lado, utilizando una balsa la cúal puede llevar solo dos personas: 
 * {(misionero - misionero), (canibal - canibal), (misionero - canibal)}. La restricción del juego consiste en
 *  que nunca en ningun lado del río, el número de canibales puede exceder el de número de misioneros.
 *  */

package canibales;

import java.util.Scanner;

public class principal {
	
	static int [][] caniYmisi = {{3,0},{3,0}};
	static int lado = 0;
	static int ladoContrario = 1; 
	static int balsa [] = {0,0};
	static boolean ganador = false;
	static int movimientosTotales = 0;
	
	public static void ingresarBalsa(String tipo)
	{
		
		if(tipo == "canibal")
		{
			caniYmisi[0][lado] --;
			caniYmisi[0][ladoContrario] ++;
			balsa[0] ++;
		}
			
		else
		{
			caniYmisi[1][lado] --;
			caniYmisi[1][ladoContrario] ++;
			balsa[1] ++;
		}
			
	}
	
	public static void dejarBalsa(String tipo)
	{
		if(tipo == "canibal")
		{
			caniYmisi[0][lado] ++;
			caniYmisi[0][ladoContrario] --;
			balsa[0] --;
		}
			
		else
		{
			caniYmisi[1][lado] ++;
			caniYmisi[1][ladoContrario] --;
			balsa[1] --;
		}
	}
	
	public static boolean  verificarRestriccion()
	{
		if((caniYmisi[0][0]>caniYmisi[1][0] &&caniYmisi[1][0]!=0) ||
				caniYmisi[0][1]>caniYmisi[1][1] && caniYmisi[1][1] !=0)
		{
			System.out.println("Se ha incumplido la restricción, juego perdido!");
			return true;
		}
		return false;	
	}
	
	public static void verificarGanador()
	{
		if(caniYmisi[0][1]==3 && caniYmisi[1][1]==3)
		{
			System.out.println("Juego Ganado!!!");
			System.out.println("movimientos Totales: " + movimientosTotales);
			if(movimientosTotales ==11)
				System.out.println("Felicitaciones has solucionado el juego con el menor costo posible.");
			ganador = true;
		}

	}
	
	public static void mostrarEstadoActual()
	{
		if (lado == 1)
		{
			System.out.println("***********************");
			System.out.println("                    \\__/");
			System.out.println("Canibales  --> | " + caniYmisi[0][0] + "    " + caniYmisi[0][1]+" |");
			System.out.println("Misioneros --> | " + caniYmisi[1][0] + "    " + caniYmisi[1][1]+" |");
			System.out.println("***********************");
		}
		else
		{
			System.out.println("***********************");
			System.out.println("              \\__/");
			System.out.println("Canibales --> | " + caniYmisi[0][0] + "  " + caniYmisi[0][1]+" |");
			System.out.println("Misioneros -->| " + caniYmisi[1][0] + "  " + caniYmisi[1][1]+" |");
			System.out.println("***********************");
		}
		
	}
	
	


	public static void main(String[] args) {
		
		int movimientos = 0;
		Scanner leer = new Scanner(System.in);
		System.out.println("**** JUEGO CANIBALES Y MISIONEROS *****");
		mostrarEstadoActual();
		while(!ganador)
		{
			try {
				System.out.println("Ingrese una opción: \n1.Agregar canibal a la balsa"
						+ "\n2.Agregar misionero a la balsa"
						+ "\n3.bajar canibal de la balsa"
						+ "\n4.bajar misionero de la balsa"
						+ "\n5.Mover Balsa");
				int opcion = leer.nextInt();
				
				switch (opcion) {
				case 1:
					if(caniYmisi[0][lado]==0)
					{
						System.out.println("No existen canibales en este lado del rio");
						break;
					}
					if(movimientos<2)
					{
						ingresarBalsa("canibal");
						movimientos++;
					}	
					else
						System.out.println("Balsa Llena, seleccione otra opción");
					break;
				case 2:
					if(caniYmisi[1][lado]==0)
					{
						System.out.println("No existen misioneros en este lado del rio");
						break;
					}
					if(movimientos<2)
					{
						ingresarBalsa("misionero");
						movimientos++;
					}	
					else
						System.out.println("Balsa Llena, seleccione otra opción");
					
					break;
				case 3:
					if(balsa[0]<=0)
						System.out.println("No se tiene ningún canibal en la balsa");
					else
					{
						dejarBalsa("canibal");
						balsa[0]--;
						movimientos--;
					}
						
					break;
				case 4:
					if(balsa[1]<=0)
						System.out.println("No se tiene ningún misionero en la balsa");
					else
					{
						dejarBalsa("misionero");
						balsa[1]--;
						movimientos--;
					}	
					
					break;
				case 5:
					if(balsa[0] ==0 && balsa[1]==0)
						System.out.println("No se ha ingresado ningún canibal o misionero a la balsa");
					else
					{
						if(verificarRestriccion())
							return;
						movimientosTotales++;
						verificarGanador();
						balsa[0]=0;
						balsa[1]=0;
						movimientos = 0;
						if(lado == 0)
						{
							lado = 1;
							ladoContrario = 0;
						}	
						else
						{
							lado =0;
							ladoContrario=1;
						}
						mostrarEstadoActual();	
					}
					break;
				default:
					break;
				}

			} catch (Exception e) {
				System.out.println("Seleccione una opción valida");
			}
		}

	}

}
