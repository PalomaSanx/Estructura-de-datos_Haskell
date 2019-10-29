package PaqTADVacioException;

/**  
*  La excepci�n TADVacioException se utiliza para manejar 
*  aquellas situaciones an�malas que se producen al intentar 
*  acceder (por consulta, borrado, etc..) a elementos b�sicos 
*  de una estructura de datos que est� vac�a. 
*/

public class TADVacioException extends Exception
{   
	

	/**
	 *   Devuelve la informaci�n de la excepci�n
	 *   @return	El String "TADVacioException"     
	 */
	public String toString()
	{
		return "TADVacioException";
	}
}