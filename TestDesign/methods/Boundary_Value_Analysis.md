#Анализ граничных значений
Анализ граничных значений - проверка граничных значений подмножества,
а также значение выше и ниже диапазона (х-1,х,х+1)
То есть мы проверяем значение системы на граничных значаниях где система меняет свое поведение, проверить результат меняется с валидного на невалидный
Если х это граничное значение то проверяем х-шаг, х, х+ шаг.

Пример:
1. Ограничение скорости 60. Проверяем 59, 60, 61.

1.Прием на работу людей: 0-14 не берем, 14 -18 неполный день, 18 - 60 полный, 60-200 не берем.
-1,0,1; 13,14,15; 17,18,19; 59,60,61; 199,200,201
