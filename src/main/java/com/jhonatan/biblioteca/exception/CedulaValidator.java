package com.jhonatan.biblioteca.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CedulaValidator implements ConstraintValidator<Cedula, String> {

    @Override
    public boolean isValid(String cedula, ConstraintValidatorContext context) {

        // 1. Validar que no sea null ni vacío
        if (cedula == null || cedula.trim().isEmpty()) {
            return false;
        }

        // 2. Validar que tenga exactamente 10 dígitos
        if (cedula.length() != 10) {
            return false;
        }

        // 3. Validar que solo contenga números
        if (!cedula.matches("\\d+")) {
            return false;
        }

        // 4. Validar que el código de provincia esté entre 01 y 24
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }

        // 5. Validar que el tercer dígito sea menor a 6 (personas naturales)
        int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
        if (tercerDigito >= 6) {
            return false;
        }

        // 6. Aplicar el algoritmo de Módulo 10
        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int suma = 0;

        for (int i = 0; i < 9; i++) {
            int digito = Integer.parseInt(cedula.substring(i, i + 1));
            int producto = digito * coeficientes[i];
            if (producto > 9) {
                producto -= 9;
            }
            suma += producto;
        }

        // 7. Calcular el dígito verificador
        int digitoVerificador = (10 - (suma % 10)) % 10;

        // 8. Comparar con el décimo dígito de la cédula
        int decimoDigito = Integer.parseInt(cedula.substring(9, 10));

        return decimoDigito == digitoVerificador;
    }
}