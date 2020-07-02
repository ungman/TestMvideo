Техники основанные на спецификации (черного ящика)
* Эквивалентных классов
* Анализа граничных значений
* Комбинаторные методы
    - Все комбинации
    - Парное тестирование
    - Выбор каждого
    - Выбор базовых
* Таблица решений
* Дерево классификаций
* Тестирование состояний
* Причина - следствие
* Сценарное тестирование
* Случайное тестирование
* Синтаксическое тестирование

Элементарные методы
- фокусируются на анализе входных/выходных параметрах
- могут сгруппированы для лучшего покрытия
- обычно не используют и не зависят от других техник

Комбинаторные методы
- возможность объединить значения нескольки  входных/выходных параметров параметров
- возможность использования с элементарными методами

Расширенные методы
-помогают ианализировать систему с точки зрения бизнес-логики
- анализ основан на данных, организованных в виде таблиц, диограмм, графов ...
- возможность использования базовых и комбинаторных методов




| Группа        |  Техники           | Использование  |
| ------------- |:-------------| -----|
Элементарный метод| Метод эквивалентных классов | Входные/выходные параметры имеют большое количество кол-во значений|
|Элементарный метод|Анализ граничных значений | Значения имеют явные границы и диапазоны, или неявные(например технические ограничения) границы.
|Комбинаторные методы |Все комбинации|Размер данных невелик, или каждый из низ приводит к определенному результату
|Комбинаторные методы|Попарное тестирование |Кол-во входных опреций чрезмерно велико|
|Комбинаторные методы|Выбор каждого парамера |Значение параметра вызывает ошибку, чаще чем их комбинации|
|Комбинаторные методы|Выбор базовых параметров|Значение определенных параметров, вызывает дефекты чаще чем их комбинации или другие параметры |
|Расширенные методы|Таблица решений|Набор комбинаций параметров и их результат |
|Расширенные методы|Дерево классификаций| Структуру можно представить в виде иерхахического дерева|
|Расширенные методы|Метод переходных состяний|Очевидные состояние системы,имеющие переходы, регулирумые правилами |
|Расширенные методы|Причина - следствие|Причины(входные пареметры) и следствия связанны большим количеством сложных параметров|
|Расширенные методы|Сценарное тестирование|Есть описанные сценарии, которые необходимо проверить|
|Другие |Случайное тестирование|При выявления несистематических дефектов|
|Другие |Синтаксическое тестирование|Имеются сложные форматы входных данных|

#### [Метод эквивалентных классов](methods/Equivalence_Partitioning.md)
Метод эквивалентных классов - разбиение пареметров на подможества, для каждого из которых система ведет себя одинаково.
#### [Анализ граничных значений](methods/Boundary_Value_Analysis.md)
Анализ граничных значений - проверка граничных значений подмножества, а также значение выше и ниже диапазона (х-1,х,х+1)
#### [Все комбинации](methods/All_Combination.md)
Все комбинации - исчерпывающее тестирование, перебирающее все возможные параметры.
#### [Попарное тестирование](methods/PairWise.md)
Попарное тестирование - метод в котором,  параметры должы быть в паре с другими хотя бы раз.
#### [Выбор каждого парамера](methods/Each_Choice_Method.md)
Выбор каждого параметра- параметр должен быть выбран минимум один раз
#### [Выбор базовых параметров](methods/Base_Choice_Method.md)
Выбор базовых параметров - выбор определенных параметров,и перебор их с фиксированными другим базовыми параметрами
#### [Таблица решений](methods/Decision_Table.md)
Таблица решений - бизнес - логика задокументирована как набор условий и действий и представленна в виде таблицы
#### [Дерево классификаций](methods/Classification_Tree_Method.md)
Дерево классификаций - отображение в виде дерева аспектов, которые влияют на поведение системы
#### [Состояний и переходов](methods/State_Transition.md)
Состояний и переходов - состояние и переходы отображаются в виде графа
#### [Причина - следствие](methods/Equivalence_Partitioning.md)
Причина- следствие - бизнес-логика задокументирована как набор условий и действий и представленна в виде графаф
#### [Сценарное тестирование](methods/Equivalence_Partitioning.md)
Сценарное тестирование - описанные сценарии(действия и результат)
#### [Случайное тестирование](methods/Equivalence_Partitioning.md)
Случайное тестирование- тестирование при котором выбирается случайное значение из возможных
#### [Синтаксическое тестирование](methods/Equivalence_Partitioning.md)
Синтаксическое тестирование - тестирование сложны вхожных данных, которые требуют валидацию