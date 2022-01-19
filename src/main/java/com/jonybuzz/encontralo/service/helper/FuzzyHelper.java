package com.jonybuzz.encontralo.service.helper;

import org.apache.commons.text.similarity.FuzzyScore;

import java.text.Normalizer;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;

public class FuzzyHelper {

    public FuzzyHelper() {
    }

    public static <O> Optional<O> buscarPorAproximacion(
            String query, Collection<O> valores,
            Function<O, String> extraerString
    ) {

        String queryNormalizado = normalizar(query);
        O valorMasParecido = null;
        float mayorPuntaje = 0;
        FuzzyScore fuzzyScore = new FuzzyScore(Locale.ENGLISH);
        for (O valor : valores) {
            String stringBuscadoNormalizado = normalizar(extraerString.apply(valor));
            if (stringBuscadoNormalizado.equalsIgnoreCase(queryNormalizado)) {
                return Optional.of(valor);
            } else {
                float puntaje = (float) (fuzzyScore.fuzzyScore(stringBuscadoNormalizado, queryNormalizado)
                        - queryNormalizado.length())
                        / stringBuscadoNormalizado.length();
                if (puntaje > mayorPuntaje) {
                    mayorPuntaje = puntaje;
                    valorMasParecido = valor;
                }
            }
        }
        if (mayorPuntaje > 0) {
            return Optional.of(valorMasParecido);
        } else {
            return Optional.empty();
        }
    }

    private static String normalizar(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

}
