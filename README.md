# Используемые технологии:

### Backend

- Java 11
- PostgreSQL 14
- Spring boot 2.7.3
- Spring JPA
- Spring Web
- Junit 5.9.0
- Maven
- InteliJ IDEA

### Frontend

- HTML
- JavaScript (Без фреймворков)
- Node js 14
- Bootstrap 5
- Font Awesome

# Установка

### 1. Инициализация БД
    
* Установите PostgreSQL
* Перейдите в bin папку PostgreSQL (К примеру "D:\PostgreSql\14\bin")
* Откройте командную строку в данной папке
* Вставьте скрипт `.\psql -U <username> -d <db_name> -f <path_to_file>`, где 
    * `<username>` - имя Вашего пользователя PostgreSQL (По умолчанию postgres)
    * `<db_name>` - имя Вашей базы данных, в которой будет создаваться таблица (По умолчанию postgres)
    * `<path_to_file>` - путь до .sql скрипта в корне папки. Указывается в кавычках

### 2. Запуск Backend
* Откройте проект `phone-book-backend` с помощью Intellij IDEA или другой среды разработки
* Откройте файл `application.yml`, находящийся по пути `src/main/resources/application.yml` и измените поля соответственно Вашей конфигурации (все значения без кавычек)
    * `<database>` - имя базы данных, в которой находится таблица, созданная скриптом
    * `<username>` - имя пользователя базы данных, под которым Вы будете подключаться
    * `<password>` - пароль пользвателя базы данных
* Запустите файл `PhoneBookApplication`, находящийся по пути `src/main/java/com/surmin/phonebook/PhoneBookApplication`. В Intellij IDEA вы можете выбрать `Edit configurations... > Add New Configuration > Application > Укажите Имя и в поле Main Class установите PhoneBookApplication`.

### 3. Запуск Frontend
* Установите Nodejs 
* Откройте проект `phone-book-front`.
* Запустите терминал в корневой папке проекта и введите команду `node index.js`

### 4. Использование проекта
 Для того, чтобы увидеть интерфейс пользователя, введите в адресную строку браузера ссылка [http://localhost:3000](http://localhost:3000) (Backend и Frontend должны быть включены)