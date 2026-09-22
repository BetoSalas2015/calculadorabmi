# Calculadora de BMI para Android

Aplicación Android que calcula el **índice de masa corporal** (*Body Mass Index*, BMI) a partir del peso y la estatura proporcionados por el usuario.

Este primer commit incluye la creación de la interfaz, la referencia de sus componentes desde Java, el cálculo del BMI y la gestión del evento `click` mediante un atributo XML.

## Objetivo

Practicar los fundamentos del desarrollo de actividades en Android:

- Crear una interfaz mediante XML.
- Definir recursos de texto en `strings.xml`.
- Asociar una actividad con su archivo de diseño.
- Referenciar componentes mediante `findViewById()`.
- Leer información de componentes `EditText`.
- Manipular un componente `TextView` desde Java.
- Gestionar eventos mediante el atributo XML `android:onClick`.

## Fórmula utilizada

El BMI se calcula mediante la siguiente fórmula:

\[
BMI = masa en kg / (estatura en m)^2
\]

La aplicación solicita la estatura en centímetros, por lo que primero debe convertirla a metros.

## Interfaz de usuario

La aplicación contiene los siguientes componentes:

| Componente | Identificador | Función |
|---|---|---|
| `TextView` | `txtPeso` | Muestra la etiqueta para capturar el peso. |
| `EditText` | `edtPeso` | Permite introducir el peso en kilogramos. |
| `TextView` | `txtAltura` | Muestra la etiqueta para capturar la estatura. |
| `EditText` | `edtEstatura` | Permite introducir la estatura en centímetros. |
| `Button` | `btnCalculo` | Inicia el cálculo del BMI. |
| `TextView` | `txtResultado` | Muestra el resultado del cálculo. |


## Gestión del evento

En este primer commit, el evento `click` se gestiona mediante el atributo XML:

```xml
android:onClick="calculaBMI"
```

Cuando el usuario presiona el botón, Android busca en la actividad el siguiente método:

```java
public void calculaBMI(View view)
```

El método debe cumplir estas condiciones:

- Ser `public`.
- Devolver `void`.
- Recibir un objeto `View` como parámetro.
- Tener el mismo nombre especificado en `android:onClick`.

## Flujo de la aplicación

1. El usuario introduce su peso en kilogramos.
2. El usuario introduce su estatura en centímetros.
3. El usuario presiona el botón **Calcular**.
4. Android invoca el método `calculaBMI(View view)`.
5. La estatura se convierte de centímetros a metros.
6. La aplicación calcula el BMI.
7. El resultado se muestra en `txtResultado`.

## Alcance de este commit

Este primer commit incluye:

- [x] Creación del proyecto Android.
- [x] Creación de `MainActivity`.
- [x] Diseño de la interfaz de usuario.
- [x] Definición de recursos en `strings.xml`.
- [x] Referencia de componentes mediante `findViewById()`.
- [x] Lectura del peso y la estatura.
- [x] Conversión de centímetros a metros.
- [x] Cálculo del BMI.
- [x] Presentación del resultado.
- [x] Gestión del evento `click` mediante `android:onClick`.

Todavía no se incluyen:

- Validación de campos vacíos.
- Manejo de valores incorrectos.
- Clasificación del resultado del BMI.
- Gestión de eventos mediante listeners.
- Clases anónimas o expresiones lambda.
- Persistencia de información.
- Manejo de cambios de configuración.

## Ejecución

1. Abrir el proyecto en Android Studio.
2. Sincronizar las dependencias de Gradle.
3. Seleccionar un emulador o dispositivo Android.
4. Ejecutar la aplicación.
5. Introducir el peso y la estatura.
6. Presionar **Calcular** para mostrar el BMI.