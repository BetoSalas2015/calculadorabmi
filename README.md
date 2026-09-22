## Gestión de eventos mediante una clase anónima

En esta actualización, el evento `click` del botón se gestiona mediante una **clase anónima**.

Una clase anónima es una clase sin nombre que se declara y se instancia al mismo tiempo. En este proyecto, la clase anónima implementa `View.OnClickListener` directamente durante el registro del listener.

Este enfoque elimina la necesidad de mantener la clase interna `CalculaBMI`.

## Registro del listener

Después de obtener la referencia al botón, se crea una clase anónima dentro de `setOnClickListener()`:

```java
btnCalculo.setOnClickListener(new View.OnClickListener() {
    // Implementación de onClick()
});
```

La expresión:

```java
new View.OnClickListener()
```

crea una instancia anónima de una clase que implementa la interfaz `View.OnClickListener`.

Como esta interfaz define el método `onClick()`, la clase anónima debe proporcionar su implementación:

```java
@Override
public void onClick(View view) {
    calculaBMI();
}
```

Cuando el usuario presiona el botón, Android ejecuta el método `onClick()` de esta clase anónima, que a su vez invoca `calculaBMI()`.

## Cambios respecto a la clase interna

En la versión anterior se utilizaba una clase interna con nombre:

```java
private class CalculaBMI implements View.OnClickListener
```

Posteriormente, se creaba una instancia para registrarla como listener:

```java
btnCalculo.setOnClickListener(new CalculaBMI());
```

En esta actualización:

- Se elimina la clase interna `CalculaBMI`.
- Se elimina la expresión `new CalculaBMI()`.
- La implementación de `View.OnClickListener` se coloca directamente en `setOnClickListener()`.
- El método `onClick()` se declara dentro de la clase anónima.
- `onClick()` continúa invocando el método `calculaBMI()` de la actividad.

## Flujo del evento

1. `MainActivity` obtiene la referencia al botón `btnCalculo`.
2. Se crea una clase anónima que implementa `View.OnClickListener`.
3. La instancia anónima se registra mediante `setOnClickListener()`.
4. El usuario presiona el botón.
5. El botón genera el evento `click`.
6. Android ejecuta el método `onClick()` de la clase anónima.
7. `onClick()` invoca `calculaBMI()`.
8. La aplicación calcula el BMI y muestra el resultado.

## Ventajas de una clase anónima

- Evita declarar una clase interna con nombre.
- Mantiene el listener cerca del componente que genera el evento.
- Reduce la cantidad de elementos declarados en la actividad.
- Resulta apropiada cuando el listener se utiliza una sola vez.
- Puede acceder a los atributos y métodos de `MainActivity`.
- Facilita la lectura de listeners breves y específicos.

## Consideraciones

Una clase anónima no puede instanciarse posteriormente por su nombre ni reutilizarse directamente en otros lugares.

Este enfoque es adecuado cuando:

- El listener se utiliza una sola vez.
- La implementación de `onClick()` es breve.
- La lógica del evento pertenece exclusivamente al componente registrado.

Si el listener contiene muchas instrucciones o debe utilizarse en varios componentes, puede ser más conveniente utilizar una clase con nombre.

La operación principal se mantiene en el método `calculaBMI()`. De esta manera, la clase anónima solo recibe el evento y delega el procesamiento:

```java
@Override
public void onClick(View view) {
    calculaBMI();
}
```

Esta separación evita colocar toda la lógica del cálculo dentro del listener.

## Alcance de esta actualización

Esta actualización incluye:

- [x] Eliminación de la clase interna `CalculaBMI`.
- [x] Creación de una clase anónima.
- [x] Implementación de `View.OnClickListener` durante el registro.
- [x] Implementación de `onClick()` dentro de la clase anónima.
- [x] Invocación de `calculaBMI()` desde `onClick()`.
- [x] Registro directo del listener mediante `setOnClickListener()`.
- [x] Conservación de la lógica del cálculo en un método separado.

Todavía no se incluyen:

- Expresiones lambda.
- Validación de campos vacíos.
- Manejo de valores incorrectos.
- Clasificación del resultado del BMI.
- Persistencia de información.
- Manejo de cambios de configuración.