## Persistencia con `SharedPreferences`

En esta actualización, la aplicación utiliza `SharedPreferences` para conservar el peso y la estatura introducidos por el usuario.

`SharedPreferences` permite almacenar información sencilla como pares **clave-valor** dentro de un archivo XML privado de la aplicación.

En este proyecto se almacenan dos valores:

| Clave | Valor |
|---|---|
| `peso` | Peso introducido por el usuario. |
| `estatura` | Estatura introducida por el usuario. |

Ambos valores se guardan como cadenas de texto porque provienen de componentes `EditText`.

## Guardado durante el ciclo de vida

La información se almacena cuando la actividad entra en pausa:

```java
@Override
protected void onPause() {
    super.onPause();
    guardarDatos();
}
```

`onPause()` puede ejecutarse cuando:

- El usuario abre otra actividad.
- Presiona el botón de inicio.
- Aparece otra interfaz sobre la aplicación.
- La aplicación deja de estar en primer plano.

El guardado puede concentrarse en un método independiente para evitar repetir instrucciones:

```java
private void guardarDatos() {
    // Obtener los textos y guardarlos en SharedPreferences.
}
```

## `onSaveInstanceState()`

`onSaveInstanceState()` está pensado principalmente para conservar el estado temporal de la interfaz cuando Android puede recrear la actividad, por ejemplo, después de un cambio de configuración.

```java
@Override
protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
}
```

Para información temporal puede utilizarse el objeto `Bundle`:

```java
outState.putString("peso", edtPeso.getText().toString());
outState.putString("estatura", edtEstatura.getText().toString());
```

Sin embargo, `onSaveInstanceState()` no sustituye al almacenamiento persistente, ya que no se garantiza su ejecución cuando el usuario cierra voluntariamente la aplicación.

En este proyecto, `SharedPreferences` es el mecanismo que permite conservar los datos entre distintas ejecuciones.

## Recuperación de información

Los datos se recuperan mediante `getString()`:

```java
String pesoGuardado = misDatos.getString("peso", "");
String estaturaGuardada = misDatos.getString("estatura", "");
```

Cada llamada recibe:

1. La clave del valor que se desea recuperar.
2. El valor predeterminado que se utilizará si la clave no existe.

En este caso, el valor predeterminado es una cadena vacía.

Los valores recuperados se colocan nuevamente en los campos:

```java
edtPeso.setText(pesoGuardado);
edtEstatura.setText(estaturaGuardada);
```

## Recuperación en `onCreate()`

Cuando se crea la actividad, las preferencias deben abrirse después de cargar la interfaz y obtener las referencias de los componentes:

```java
misDatos = getSharedPreferences("Datos", MODE_PRIVATE);
restaurarDatos();
```

El método auxiliar puede concentrar la recuperación:

```java
private void restaurarDatos() {
    // Leer las preferencias y actualizar los EditText.
}
```

De esta forma, si la aplicación fue cerrada y posteriormente ejecutada otra vez, el peso y la estatura aparecen nuevamente en la interfaz.

## Recuperación en `onResume()`

También es posible actualizar los campos cuando la actividad regresa al primer plano:

```java
@Override
protected void onResume() {
    super.onResume();
    restaurarDatos();
}
```

Este método se ejecuta cuando la actividad vuelve a estar disponible para interactuar con el usuario.

Si los valores solo pueden modificarse desde esta actividad, recuperarlos en `onCreate()` suele ser suficiente. `onResume()` resulta útil cuando otra parte de la aplicación también puede modificar las preferencias.

## Flujo de persistencia

1. La actividad crea o abre el archivo de preferencias `Datos`.
2. Se recuperan los valores asociados con `peso` y `estatura`.
3. Los valores se muestran en los campos correspondientes.
4. El usuario modifica la información.
5. La actividad entra en pausa.
6. Los textos se guardan mediante un `SharedPreferences.Editor`.
7. Los cambios se aplican con `apply()`.
8. En la siguiente ejecución, la información se recupera mediante `getString()`.

## Ventajas de `SharedPreferences`

- Tiene una implementación sencilla.
- Almacena información como pares clave-valor.
- Conserva los datos después de cerrar la aplicación.
- Permite recuperar valores desde distintos componentes de la aplicación.
- Resulta apropiado para preferencias y pequeñas cantidades de información.
- No requiere crear ni administrar una base de datos.

## Limitaciones

`SharedPreferences` está diseñado para datos pequeños y simples, como:

- Preferencias del usuario.
- Opciones de configuración.
- Banderas booleanas.
- Cadenas y valores numéricos sencillos.

No es la alternativa adecuada para:

- Grandes cantidades de información.
- Datos relacionados entre sí.
- Listas complejas de objetos.
- Información que requiere consultas o búsquedas avanzadas.

Para esos casos debe utilizarse una solución como una base de datos.

## Consideraciones de seguridad

Aunque `MODE_PRIVATE` impide que otras aplicaciones accedan normalmente al archivo, no debe utilizarse `SharedPreferences` sin protección adicional para guardar:

- Contraseñas.
- Tokens de acceso.
- Información bancaria.
- Datos personales sensibles.

## Alcance de esta actualización

Esta actualización incluye:

- [x] Declaración de un objeto `SharedPreferences`.
- [x] Creación o apertura del archivo de preferencias `Datos`.
- [x] Uso de `MODE_PRIVATE`.
- [x] Recuperación del peso y la estatura.
- [x] Restauración de valores en los componentes `EditText`.
- [x] Creación de un objeto `SharedPre 