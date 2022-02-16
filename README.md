# Android only app
Здравствуйте!
Данное приложение является тестовым. 
Оно создано с целью продемонстрировать личный вариант решения поставленной задачи.

Итак, короткое объяснение алгоритма работы приложения:
1. При запуске приложения происходит проверка наличия сохраненной информации о курсе валют (если она имеется переходим к шагу #3, иначе #2)
2. Происходит асинхронный запрос к удаленному ресурсу.
3. Полученная информация обрабатывается в форму, подходящую под Entity.
4.1. Если полученная информация была из сохранений, то она проверяется на валидность по дате 
(если дата последнего запроса старше на день от текущей то возвращаемся к шагу #2) 
4.2. Если полученная информация была от удаленного ресурса, то она не проверяется на ввалидность и сохраняется
