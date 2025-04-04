# Приложение для управления товарами

Это Android приложение позволяет пользователям управлять товарами в локальной базе данных. Приложение предоставляет функционал для добавления, просмотра, редактирования и удаления товаров. Все данные хранятся в базе данных SQLite, а товары отображаются в списке для удобного управления.

## Функциональность

1. **Добавление товара**: Пользователи могут добавить новый товар, указав его название, цену и описание.
2. **Просмотр товаров**: Пользователи могут просматривать список всех товаров, хранящихся в базе данных.
3. **Редактирование товара**: Пользователи могут редактировать данные (название, цену, описание) существующего товара.
4. **Удаление товара**: Пользователи могут удалить товар из базы данных.

## Требования

- Android Studio
- Android SDK 21 или выше
- Физическое устройство или эмулятор Android для запуска приложения

## Используемые технологии

- **Android SDK**: Для разработки мобильного приложения.
- **SQLite**: Для работы с локальной базой данных.
- **Java**: Язык программирования для логики приложения.

## Установка

1. **Клонировать репозиторий**:
   ```bash
   git clone https://github.com/IgorUshakov05/product-list.git
   ```

2. **Открыть проект в Android Studio**:
   - Откройте Android Studio и выберите **Open an existing project**.
   - Перейдите в папку, где вы клонировали проект, и выберите её.

3. **Запустить приложение**:
   - Подключите физическое Android-устройство или запустите эмулятор.
   - Нажмите на кнопку **Run** (зелёная стрелка) в Android Studio для компиляции и запуска приложения.

## Структура проекта

```
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── example/
│   │       │           └── myapplication/
│   │       │               ├── DatabaseHelper.java      // Операции с базой данных
│   │       │               ├── MainActivity.java        // Экран добавления товара
│   │       │               ├── ProductCard.java         // Класс-сущность товара
│   │       │               └── ProductListActivity.java // Экран просмотра товаров
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_main.xml       // Макет для добавления товара
│   │       │   │   ├── activity_product_list.xml // Макет для просмотра товаров
│   │       │   └── values/
│   │       │       └── colors.xml              // Определения цветов
│   │       └── AndroidManifest.xml             // Манифест приложения
```

## Схема базы данных

База данных состоит из одной таблицы `products` с следующими колонками:

- `id` (INTEGER PRIMARY KEY AUTOINCREMENT): Уникальный идентификатор товара.
- `name` (TEXT): Название товара.
- `price` (REAL): Цена товара.
- `description` (TEXT): Описание товара.

## Как это работает

1. **MainActivity**: Это основной экран, где пользователи могут ввести данные о новом товаре (название, цена, описание) и нажать **Добавить товар** для сохранения товара в базу данных.
   - **Добавление товара**: Данные сохраняются в базе данных SQLite с помощью класса `DatabaseHelper`.
   - **Просмотр товаров**: Пользователь может перейти к списку товаров, нажав кнопку **Просмотр товаров**.

2. **ProductListActivity**: Этот экран отображает список всех товаров, сохранённых в базе данных SQLite.
   - Товары загружаются в `ListView` с помощью `Cursor` из базы данных.
   - Каждый товар отображается в простом списке с названием, ценой и описанием.

3. **DatabaseHelper**: Этот класс выполняет все операции с базой данных, такие как:
   - `addProduct`: Добавляет новый товар в базу данных.
   - `getAllProducts`: Извлекает все товары из базы данных.
   - `getProductById`: Извлекает конкретный товар по его ID.
   - `updateProduct`: Обновляет данные существующего товара.
   - `deleteProduct`: Удаляет товар по его ID.

## Возможные улучшения

1. **Обработка ошибок**: Улучшить обработку ошибок (например, для недопустимых данных пользователя).
2. **Валидация данных**: Добавить валидацию для ввода данных пользователем, чтобы, например, цена была положительным числом.
3. **Поиск**: Реализовать функционал поиска товаров по названию или категории.

## Лицензия

Этот проект лицензирован под лицензией MIT — подробности смотрите в файле [LICENSE](LICENSE).

---

