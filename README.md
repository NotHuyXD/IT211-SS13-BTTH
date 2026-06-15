# HCM-K24-CNTT2_LeHuuNhatHao_KiemTraThucHanhSS13

Bài kiểm tra thực hành Java Web Service - Session 13: Logging và Unit Testing.

## Công nghệ

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Spring AOP
- MySQL Driver
- Lombok
- JUnit 5
- Mockito
- MockMvc
- Logback

## Chức năng REST API

Base URL: `http://localhost:8080/api/books`

| Method | Endpoint | Chức năng |
|---|---|---|
| GET | `/api/books` | Lấy danh sách sách |
| GET | `/api/books/{id}` | Lấy sách theo id |
| POST | `/api/books` | Thêm sách |
| PUT | `/api/books/{id}` | Cập nhật toàn bộ sách |
| PATCH | `/api/books/{id}` | Cập nhật một phần sách |
| DELETE | `/api/books/{id}` | Xóa sách |

## Cấu hình MySQL

File cấu hình nằm tại:

```text
src/main/resources/application.properties
```

Mặc định project dùng database:

```sql
CREATE DATABASE library_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Nếu MySQL của bạn khác mật khẩu, sửa dòng sau:

```properties
spring.datasource.username=root
spring.datasource.password=123456
```

## Logging

File cấu hình:

```text
src/main/resources/logback-spring.xml
```

Đã cấu hình:

- Console Appender: in log ra terminal.
- File Appender: ghi log vào `logs/library-app.log`.
- RollingPolicy theo ngày.
- Giữ tối đa 7 file log.
- Root level: `INFO`.
- Package `com.example.library`: `DEBUG`.

## Unit Test

Chạy toàn bộ test:

```bash
mvn test
```

Đã có đủ 6 test case theo đề:

### BookServiceTest

- `getAllBooks_returnList`
- `getBookById_found`
- `getBookById_notFound`

### BookControllerTest

- `getAllBooks_returnOkAndJsonList`
- `getBookById_found_returnOkAndJsonObject`
- `getBookById_notFound_returnNotFound`

## JSON mẫu để POST

```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "9780132350884",
  "publicationYear": 2008
}
```
