# Задание:
Продемонстрируйте использование нескольких паттернов проектирования в вашем приложении. Объясните, почему вы выбрали эти паттерны и как они помогают в решении конкретных проблем.

1. Паттерн Фасад (Facade)
Паттерн Фасад предоставляет упрощенный интерфейс для сложной системы, например, если у нас в приложении было бы несколько сервисов под разные задачи с использованием Person.
Создал класс PersonFacade, который будет взаимодействовать с сервисами для управления объектами Person.

3. Паттерн Строитель (Builder)
Паттерн Строитель позволяет создавать сложные объекты поэтапно. Создал подкласс Builder для создания объектов Person.

4. Паттерн Адаптер (Adapter)
Паттерн Адаптер позволяет объектам с несовместимыми интерфейсами работать вместе. Предположим, у нас есть внешний API для получения информации о человеке, и мы хотим адаптировать его для использования в нашем приложении.
Для этого реализовали интерфейс под дополнительную базу данных, описал класс ExternalPerson для передачи данных между слоями приложения и класс ExternalPersonAdapter для адаптации данных под наше приложение. В нем использовал паттерн Builder.

Использование паттернов проектирования в нашем приложении помогает решить несколько проблем:
- Фасад упрощает взаимодействие с системой, скрывая сложные детали реализации.
- Строитель позволяет создавать объекты Person с гибкостью и удобством, избегая громоздких конструкторов.
- Адаптер обеспечивает совместимость между нашим приложением и внешними сервисами, позволяя использовать их функциональность без изменения существующего кода.
