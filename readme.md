# Documentación Lamp REST API

API Rest para operaciones CRUD sobre lamparas.
## Tecnologias:
- **Lenguaje Java**
- **Framework : Spring Boot**
- **Base de datos : MySQL y H2.**
- Se desarrollo usando MySQL pero tambien se configuro H2 ya que es una base de datos en memoria
  y facilita las pruebas, con solo ejecutar el proyecto ya se puede consumir los endpoints.
- En el caso de querer utilizar MySQL hay que modificar el application.properties con los datos de conexion.



## Endpoints Disponibles

### Listar Lamparas

- **URL:** `/api/lamps`
- **Método HTTP:** GET
- **Descripción:** Recupera una lista de lamparas disponibles.
- **Respuesta Exitosa:** Código de estado HTTP 200 OK
- **Respuesta de Ejemplo:**
  ```json
  [
    {
        "id": 91,
        "name": "Lamp1",
        "description": "descripcion1",
        "price": 100.0,
        "amount": 1
    }
    },
    // Otras lamparas...
  ]

### Obtener Detalles de una Lampara por Id

- **URL:** `/api/lamps/{id}`
- **Método HTTP:** GET
- **Descripción:** Obtiene detalles de una lampara específico utilizando su ID.
- **Parámetros de URL:**
    - `id` (Long) - ID de la lampara que se quiere obtener.
- **Respuesta Exitosa:** Código de estado HTTP 200 OK
- **Respuesta de Ejemplo:**
  ```json
  {
    {
    "id": 91,
    "name": "Lamp1",
    "description": "descripcion1",
    "price": 100.0,
    "amount": 1
    }

  }

### Agregar una nueva lampara

- **URL:** `/api/lamps`
- **Método HTTP:** POST
- **Descripción:** Agrega una nueva lampara a la base de datos.
- **Respuesta Exitosa:** Código de estado HTTP 201 Created
- **Cuerpo de la Solicitud (JSON):**
  ```json
  {
    {
    "name":"Lampara nueva",
    "description":"Descripcion del producto..",
    "price": 100,
    "amount": 1
    }
  }

### Actualizar una Lampara existente

- **URL:** `/api/lamps`
- **Método HTTP:** PUT
- **Descripción:** Actualiza los campos que desees de una lampara existente
- **Respuesta Exitosa:** Código de estado HTTP 200 OK
- **Cuerpo de la Solicitud (JSON):**
  ```json
  {
    {
        "id": 1,
        "name": "Lampara",
        "description": "Descripcion nueva",
        "price": 150.0,
        "amount": 2
    }
  }


### Eliminar una Lampara

- **URL:** `/api/lamps/{id}`
- **Método HTTP:** DELETE
- **Descripción:** Elimina una lampara de la base de datos utilizando su ID.
- **Parámetros de URL:**
    - `id` (Long) - ID de la lampara que se quiere eliminar.
- **Respuesta Exitosa:** Código de estado HTTP 200 OK
- **Respuesta de Ejemplo:**
  ```json
  {
    The lamp has been successfully deleted
  }

### Buscar una Lampara por nombre o parte del nombre

-- **URL:** `/api/lamps/getBy`
- **Método HTTP:** GET
- **Descripción:** Busca una lampara en la base de datos por su nombre (sino que busque por parte de su nombre)
- **Parámetros de URL:**
    - getBy?keyword= `clave`
- **Respuesta Exitosa:** Código de estado HTTP 200 OK

- **Respuesta de Ejemplo:**
  ```json
  [
    {
        "id": 1,
        "name": "Lampara",
        "description": "descripcion del producto",
        "price": 100.0,
        "amount": 1
    }
  ]

### Obtener todos los productos ordenados por precio
-- **URL:** `/api/lamps/getAllByOrder`
- **Método HTTP:** GET
- **Descripción:** Trae todas las lamparas ordenados por precio en forma ascendente
- **Respuesta Exitosa:** Código de estado HTTP 200 OK

  - **Respuesta de Ejemplo:**
    ```json
    [
       {
          "id": 93,
          "name": "Lamp3",
          "description": "descripcion3",
          "price": 50.0,
          "amount": 3
      },
      {
          "id": 91,
          "name": "Lamp1",
          "description": "descripcion1",
          "price": 100.0,
          "amount": 1
      },
      {
          "id": 94,
          "name": "Lamp4",
          "description": "descripcion4",
          "price": 150.0,
          "amount": 2
      }
       // Otras lamparas...
    ]