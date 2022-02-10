[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=6975594&assignment_repo_type=AssignmentRepo)
# BookServer: задание по тренингу High Performance

Разработать сервис (с использованием любых технологий), который позволит:
- Добавить 100000 книг в хранилище (использовать случайно сгенерированные названия, например цифры)
- Получить список книг, содержащих подстроку (любое слово/число)
- Написать тест, который оценит время, которое тратится на каждую операцию

Пример: “Book16342 by AuthorName534535 AuthorSurname2356”

Пример поиска по подстроке:
- Книга называется «Java Performance by Vladimir Sonkin, 2022»
- Книга должна находиться по словам «Java», «Performance», "Sonkin Java", "Java Sonkin" и т.д., указанным в любом порядке
- Добавьте несколько нецифровых названий книг для проверки правильности поиска (напишите тест)

Разработать REST-контроллер для добавления книги и поиска по ключевому слову.
Разработать нагрузочный тест jMeter, который:
- Оценит время поиска книги по подстроке
- Выдержит 1000 клиентов за 1 сек
- Выдержит еще больше клиентов (напишите, сколько)

Запуск тестов
- mvn gatling:test -Dgatling.simulationClass=computerdatabase.BasicSimulation