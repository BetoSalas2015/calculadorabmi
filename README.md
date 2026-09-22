## Gestión de Eventos Centralizada

En esta actualización, la gestión del evento `click` deja de realizarse mediante el atributo `android:onClick` del archivo XML.

Ahora se utiliza el modelo **source/listener**, en el que intervienen dos elementos:

- **Source:** objeto que genera el evento. En este proyecto es el botón `btnCalculo`.
- **Listener:** objeto responsable de recibir el evento y ejecutar una acción. En este caso es la propia actividad `MainActivity`.

Se considera una gestión centralizada porque `MainActivity` administra directamente los eventos generados por los componentes de su interfaz.

### Implementación de `OnClickListener`

La actividad implementa la interfaz `View.OnClickListener`:

```java
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener
```

Esta interfaz obliga a implementar el método:

```java
@Override
public void onClick(View view) {
    calculaBMI();
}
```

Cuando ocurre el evento, `onClick()` invoca el método encargado de calcular y mostrar el BMI.

### Registro del listener

Después de obtener la referencia al botón mediante `findViewById()`, la actividad se registra como su listener:

```java
btnCalculo = findViewById(R.id.btnCalculo);
btnCalculo.setOnClickListener(this);
```

El argumento `this` representa la instancia actual de `MainActivity`, que puede actuar como listener porque implementa `View.OnClickListener`.

### Cambio en el archivo XML

El botón ya no necesita declarar el atributo:

```xml
android:onClick="calculaBMI"
```

La relación entre el botón y el manejador del evento se establece completamente desde Java mediante `setOnClickListener()`.

### Flujo del evento

1. El usuario introduce su peso y estatura.
2. El usuario presiona el botón **Calcular**.
3. `btnCalculo` genera un evento `click`.
4. El botón notifica el evento al listener registrado.
5. Android ejecuta el método `onClick()`.
6. `onClick()` invoca `calculaBMI()`.
7. La aplicación calcula el BMI y muestra el resultado.

## Ventajas de la gestión centralizada

- Mantiene la lógica de eventos dentro de la actividad.
- Evita depender del atributo `android:onClick`.
- Permite comprobar errores durante la compilación.
- Facilita que una actividad atienda eventos de varios componentes.
- Aplica explícitamente el modelo `source/listener` de Android.

Si la actividad administra varios componentes, el parámetro recibido por `onClick()` puede utilizarse para identificar cuál generó el evento:

```java
@Override
public void onClick(View view) {
    if (view.getId() == R.id.btnCalculo) {
        calculaBMI();
    }
}
```

En la versión actual solo existe un botón generador de eventos, por lo que no es necesario realizar esta comprobación.

## Alcance de esta actualización

Esta actualización incluye:

- [x] Implementación de `View.OnClickListener` en `MainActivity`.
- [x] Implementación del método `onClick()`.
- [x] Registro del botón mediante `setOnClickListener()`.
- [x] Uso de la actividad como listener mediante `this`.
- [x] Invocación de `calculaBMI()` desde `onClick()`.
- [x] Eliminación del atributo `android:onClick` del botón.
- [x] Aplicación del modelo `source/listener`.

Todavía no se incluyen:

- Gestión descentralizada mediante una clase interna.
- Clases anónimas.
- Expresiones lambda.
- Validación de campos vacíos.
- Manejo de valores incorrectos.
- Clasificación del resultado del BMI.
- Persistencia de información.
- Manejo de cambios de configuración.