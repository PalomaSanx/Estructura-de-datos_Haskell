package PaqTADVacioException;

/**  
*  La excepción TADVacioException se utiliza para manejar 
*  aquellas situaciones anómalas que se producen al intentar 
*  acceder (por consulta, borrado, etc..) a elementos básicos 
*  de una estructura de datos que está vacía. 
*/

public class TADVacioException extends Exception
{   
	

	/**
	 *   Devuelve la información de la excepción
	 *   @return	El String "TADVacioException"     
	 */
	public String toString()
	{
		return "TADVacioException";
	}
}