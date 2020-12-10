package br.com.fctecno.doacao.domain.util;

import java.util.ArrayList;
import java.util.List;

import br.com.fctecno.doacao.domain.model.TipoSanguineo;

public class Utils {

	public static List<TipoSanguineo> listaDoadores(TipoSanguineo tipo) {

		List<TipoSanguineo> retorno = new ArrayList<TipoSanguineo>();

		switch (tipo) {
		case A_POSITIVO:
			retorno = List.of(TipoSanguineo.A_POSITIVO, TipoSanguineo.A_NEGATIVO, TipoSanguineo.O_POSITIVO,
					TipoSanguineo.O_NEGATIVO);
			break;
		case A_NEGATIVO:
			retorno = List.of(TipoSanguineo.A_NEGATIVO, TipoSanguineo.O_NEGATIVO);
			break;
		case B_POSITIVO:
			retorno = List.of(TipoSanguineo.B_POSITIVO, TipoSanguineo.B_NEGATIVO, TipoSanguineo.O_POSITIVO,
					TipoSanguineo.O_NEGATIVO);
			break;
		case B_NEGATIVO:
			retorno = List.of(TipoSanguineo.B_NEGATIVO, TipoSanguineo.O_NEGATIVO);
			break;
		case AB_POSITIVO:
			retorno = List.of(TipoSanguineo.A_POSITIVO, TipoSanguineo.A_NEGATIVO, TipoSanguineo.B_POSITIVO,
					TipoSanguineo.B_NEGATIVO, TipoSanguineo.O_POSITIVO, TipoSanguineo.O_NEGATIVO,
					TipoSanguineo.AB_POSITIVO, TipoSanguineo.AB_NEGATIVO);
			break;
		case AB_NEGATIVO:
			retorno = List.of(TipoSanguineo.A_NEGATIVO, TipoSanguineo.B_NEGATIVO, TipoSanguineo.O_NEGATIVO,
					TipoSanguineo.AB_NEGATIVO);
			break;
		case O_POSITIVO:
			retorno = List.of(TipoSanguineo.O_POSITIVO, TipoSanguineo.O_NEGATIVO);
			break;
		case O_NEGATIVO:
			retorno = List.of(TipoSanguineo.O_NEGATIVO);
			break;
		default:
			break;
		}

		return retorno;

	}
}
