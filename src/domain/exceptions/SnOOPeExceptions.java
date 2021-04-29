package domain.exceptions;

public class SnOOPeExceptions extends Exception{
    public static final String  NO_NAME_GIVEN = "Debe ingresar el nombre del jugador ";
    public static final String  NO_HEAD_COLOR_CHOSEN = "Debe seleccionar el color de la cabeza de cada jugador";
    public static final String  NO_BODY_COLOR_CHOSEN = "Debe seleccionar el color del cuerpo de cada jugador";
    public static final String  ALL_FIELDS_ARE_MANDATORY = "Debe ingresar un valor en cada campo";

    public SnOOPeExceptions(String msg){
        super(msg);
    }
}
