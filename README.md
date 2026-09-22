## Gestión de eventos mediante una clase interna

En esta actualización, `MainActivity` deja de actuar directamente como listener. La responsabilidad de atender el evento `click` se delega a una **clase interna** llamada `CalculaBMI`.

Este modelo aplica una gestión descentralizada de eventos:

- **Source:** el botón `btnCalculo`, que genera el evento.
- **Listener:** una instancia de la clase interna `CalculaBMI`.
- **Acción:** invocar el método `calculaBMI()` de la actividad.

## Clase interna `CalculaBMI`

Dentro de `MainActivity` se declara una clase privada que implementa la interfaz `View.OnClickListener`:

```java
private class CalculaBMI implements View.OnClickListener
```

Al implementar esta interfaz, la clase interna debe sobrescribir el método `onClick()`:

```java
@Override
public void onClick(View view) {
    calculaBMI();
}
```

Cuando el botón genera el evento, Android ejecuta este método. La clase interna puede invocar directamente `calculaBMI()` porque pertenece a una instancia de `MainActivity`.

## Registro del listener

Después de obtener la referencia del botón, se crea una instancia de `CalculaBMI` y se registra como listener:

```java
btnCalculo = findViewById(R.id.btnCalculo);
btnCalculo.setOnClickListener(new CalculaBMI());
```

La expresión:

```java
new CalculaBMI()
```

crea un objeto de la clase interna. Este objeto se convierte en el responsable de recibir y procesar los eventos `click` del botón.

## Cambios respecto a la gestión centralizada

En la versión anterior, `MainActivity` implementaba directamente `View.OnClickListener`:

```java
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener
```

Además, la propia actividad se registraba como listener mediante:

```java
btnCalculo.setOnClickListener(this);
```

En esta actualización:

- `MainActivity` ya no implementa `View.OnClickListener`.
- El método `onClick()` deja de pertenecer directamente a la actividad.
- La clase interna `CalculaBMI` implementa `View.OnClickListener`.
- El botón recibe una instancia de `CalculaBMI` como listener.
- El método `calculaBMI()` permanece en `MainActivity`.

## Flujo del evento

1. La actividad obtiene la referencia al botón `btnCalculo`.
2. Se crea una instancia de la clase interna `CalculaBMI`.
3. La instancia se registra mediante `setOnClickListener()`.
4. El usuario presiona el botón.
5. `btnCalculo` genera el evento `click`.
6. Android ejecuta el método `onClick()` de `CalculaBMI`.
7. `onClick()` invoca el método `calculaBMI()`.
8. La aplicación calcula el BMI y muestra el resultado.

## Ventajas de utilizar una clase interna

- Separa el manejo del evento de las responsabilidades principales de la actividad.
- Evita que `MainActivity` implemente directamente la interfaz del listener.
- Agrupa la lógica relacionada con un evento específico.
- Permite crear diferentes listeners para distintos componentes.
- La clase interna puede acceder a los atributos y métodos de `MainActivity`.
- Facilita la reutilización del listener dentro de la misma actividad.

## Consideraciones

La clase `CalculaBMI` se declara como `private` porque solamente se utiliza dentro de `MainActivity`:

```java
private class CalculaBMI
```

Este listener sigue dependiendo de la actividad, por lo que no está diseñado para reutilizarse directamente desde otras clases.

Si su implementación solo se utiliza una vez y contiene pocas instrucciones, posteriormente puede sustituirse por una clase anónima o una expresión lambda.

## Alcance de esta actualización

Esta actualización incluye:

- [x] Eliminación de `View.OnClickListener` de la declaración de `MainActivity`.
- [x] Creación de la clase interna privada `CalculaBMI`.
- [x] Implementación de `View.OnClickListener` en la clase interna.
- [x] Implementación de `onClick()` dentro de `CalculaBMI`.
- [x] Invocación de `calculaBMI()` desde el listener.
- [x] Creación de una instancia mediante `new CalculaBMI()`.
- [x] Registro de la instancia con `setOnClickListener()`.
- [x] Separación de la gestión del evento respecto de la actividad principal.

Todavía no se incluyen:

- Clases anónimas.
- Expresiones lambda.
- Validación de campos vacíos.
- Manejo de valores incorrectos.
- Clasificación del resultado del BMI.
- Persistencia de información.
- Manejo de cambios de configuración.