## Gestión de eventos mediante expresiones lambda

En esta actualización, la clase anónima utilizada para gestionar el evento `click` se sustituye por una **expresión lambda**.

Una expresión lambda es una forma breve de implementar una función que se utilizará en un lugar específico. En Java, puede emplearse con interfaces funcionales, es decir, interfaces que contienen un único método abstracto.

`View.OnClickListener` es una interfaz funcional porque define solamente el método:

```java
void onClick(View view);
```

Por esta razón, su implementación puede representarse mediante una expresión lambda.

## Sintaxis de una expresión lambda

La estructura general de una expresión lambda es:

```java
parámetros -> acción
```

En el listener del botón se utiliza:

```java
view -> calculaBMI()
```

Sus elementos son:

- `view`: parámetro recibido por el método `onClick()`.
- `->`: operador lambda que separa los parámetros de la implementación.
- `calculaBMI()`: acción que se ejecuta cuando ocurre el evento.

## Registro del listener

El botón registra el listener mediante una sola instrucción:

```java
btnCalculo.setOnClickListener(view -> calculaBMI());
```

Cuando el usuario presiona el botón:

1. Android detecta el evento `click`.
2. Se ejecuta la expresión lambda.
3. La lambda recibe el objeto `View` que generó el evento.
4. Se invoca el método `calculaBMI()`.
5. La aplicación calcula y muestra el BMI.

## Cambio respecto a la clase anónima

En la versión anterior, el listener se implementaba mediante una clase anónima:

```java
new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        calculaBMI();
    }
}
```

La expresión lambda representa el mismo comportamiento:

```java
view -> calculaBMI()
```

En esta actualización:

- Se elimina la declaración explícita de `new View.OnClickListener()`.
- Se elimina la anotación `@Override`.
- Se elimina la declaración explícita del método `onClick()`.
- Se conserva el parámetro `view`.
- Se conserva la llamada a `calculaBMI()`.
- El código del listener se vuelve más breve.

## Lambdas con varias instrucciones

Cuando una lambda ejecuta una sola instrucción, no necesita llaves:

```java
view -> calculaBMI()
```

Si debe ejecutar varias instrucciones, se utiliza un bloque:

```java
view -> {
    calculaBMI();
    // Otra instrucción
}
```

Las instrucciones del bloque deben terminar con punto y coma.

## Parámetro no utilizado

La interfaz `View.OnClickListener` proporciona el componente que generó el evento mediante el parámetro `view`.

Aunque el cálculo actual no utiliza directamente este parámetro, debe declararse porque forma parte de la firma de `onClick()`:

```java
view -> calculaBMI()
```

Si un mismo listener atendiera varios componentes, `view` podría utilizarse para identificar cuál generó el evento.

## Ventajas de las expresiones lambda

- Reducen la cantidad de código repetitivo.
- Evitan crear una clase interna o anónima explícita.
- Mantienen el comportamiento cerca del componente que genera el evento.
- Mejoran la legibilidad cuando la acción es breve.
- Permiten expresar claramente la relación entre el evento y la acción.
- Son apropiadas para listeners que se utilizan una sola vez.

## Consideraciones

Las expresiones lambda son recomendables cuando la implementación es corta y fácil de comprender.

Si la lógica del evento es extensa, conviene mantenerla en un método separado y utilizar la lambda únicamente para invocarlo:

```java
view -> calculaBMI()
```

Esto evita colocar toda la lógica de cálculo dentro del listener y conserva una separación clara de responsabilidades.

Si un listener debe reutilizarse en varios componentes o contiene lógica compleja, podría ser más conveniente utilizar una clase con nombre.

## Alcance de esta actualización

Esta actualización incluye:

- [x] Sustitución de la clase anónima por una expresión lambda.
- [x] Registro del evento mediante `setOnClickListener()`.
- [x] Uso del parámetro `view`.
- [x] Invocación de `calculaBMI()` desde la lambda.
- [x] Conservación del cálculo en un método independiente.
- [x] Reducción del código necesario para gestionar el evento.

Todavía no se incluyen:

- Validación de campos vacíos.
- Manejo de valores incorrectos.
- Clasificación del resultado del BMI.
- Persistencia de información.
- Manejo de cambios de configuración.