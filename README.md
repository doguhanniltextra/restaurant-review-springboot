

# 🍽️ Restaurant Review API with Spring Boot & Elasticsearch

This is a learning-focused backend project based on a tutorial by **Devtiro**, where I implemented a full-featured restaurant review and search system using **Spring Boot** and **Elasticsearch**.

The goal of this project is to explore:
- How Elasticsearch works with real-world search use cases
- Geo-location based queries
- DTO-Entity separation and layered architecture
- Spring Data Elasticsearch capabilities

---

## 🚀 Features

- ✅ Add, update and delete restaurants
- ✅ Upload and attach photos to restaurants
- ✅ Nested object support: Address, Reviews, Operating Hours
- ✅ Geolocation-based search using latitude, longitude, and radius
- ✅ Fuzzy search for restaurant names and cuisines
- ✅ Minimum average rating filter
- ✅ Pagination support via `Pageable`
- ✅ Elasticsearch custom queries using `@Query`

---

## 🧰 Technologies Used

| Tech                  | Purpose                                |
|-----------------------|----------------------------------------|
| **Spring Boot**       | REST API framework                     |
| **Spring Data**       | Repository abstraction                 |
| **Spring Data Elastic**| Elasticsearch integration             |
| **Elasticsearch**     | Search and filtering engine            |
| **Lombok**            | Boilerplate reduction (`@Data`, etc.)  |
| **Java 17+**          | Language & JVM platform                |

---

## 🗺️ Endpoints Overview (examples)

| Method | Endpoint                  | Description                       |
|--------|---------------------------|-----------------------------------|
| `POST` | `/restaurants`            | Create a new restaurant           |
| `GET`  | `/restaurants/{id}`       | Get restaurant by ID              |
| `GET`  | `/restaurants/search`     | Search by name, rating, location  |
| `PUT`  | `/restaurants/{id}`       | Update a restaurant               |
| `DELETE`| `/restaurants/{id}`      | Delete a restaurant               |

---

## 📂 Project Structure

```

src/
├── domain/                # Entity classes (Elasticsearch docs)
│   └── entities/
├── dto/                   # DTOs for API request/response
├── mapper/                # Manual mappers between DTO <-> Entity
├── repository/            # Spring Data Elasticsearch Repos
├── service/               # Business logic
├── controller/            # REST endpoints
└── config/                # Configs (e.g. CORS)

````

---

## 🧪 Example Elasticsearch Query

Example: Search restaurants within 5km and with fuzzy matching on name:
```json
{
  "bool": {
    "must": [
      {
        "geo_distance": {
          "distance": "5km",
          "geoLocation": {
            "lat": 41.015137,
            "lon": 28.979530
          }
        }
      },
      {
        "match": {
          "name": {
            "query": "burgar",
            "fuzziness": "AUTO"
          }
        }
      }
    ]
  }
}
````

---

## 🎯 What I Learned

* How to use Spring Data with Elasticsearch for building real-world APIs.
* Writing custom queries using Elasticsearch's query DSL inside Java annotations.
* Best practices for using DTOs and separating them from Entities.
* How pagination (`Page` and `Pageable`) works in Spring.
* The power of geo-distance and fuzzy matching in search-based apps.

---

## 📌 Tutorial Source

This project was built by following Devtiro’s [Elasticsearch + Spring Boot series](https://www.youtube.com/@devtiro).

---

## 👨‍💻 Run Locally

```bash
# Make sure Elasticsearch is running (default: localhost:9200)
# Then run the app

./mvnw spring-boot:run
```

