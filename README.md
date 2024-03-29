# Втора лабораториска вежба по Софтверско инженерство

## Давид Гичев, бр. на индекс 196034


###  Control Flow Graph

![Dijagram](./dijagram.png)

### Цикломатска комплексност

Цикломатската комплексност на овој код е 7. Ова може да се пресмета со собирање на бројот на предикатни јазли со 1, или со користење на формулата
E - N + 2\*P
Каде Е = 24, N = 19, P = 1

### Тест случаи според критериумот Multiple Condition

`(hr < 0 || hr > 24)`
Комбинации | Тестови
--|--
TX | Time(-1, 0, 0)
FT | Time(26, 0, 0)
FF | Time(10, 0, 0)

`(hr < 24)`
Комбинации | Тестови
--|--
T | Time(-1, 0, 0)
F | Time(26, 0, 0)

`(hr < 0)`
Комбинации | Тестови
--|--
T | Time(-1, 0, 0)
F | Time(26, 0, 0)

`(min < 0 || min > 59)`
Комбинации | Тестови
--|--
TX | Time(0, -1, 0)
FT | Time(0, 100, 0)
FF | Time(10, 0, 0)

`(sec >= 0 && sec <= 59)`
Комбинации | Тестови
--|--
TT | Time(10, 0, 0)
TF | Time(0, 0, 100)
FX | Time(0, 0, -1)

`(hr == 24 && min == 0 && sec == 0)`
Комбинации | Тестови
--|--
TTT | Time(24, 0, 0)
TTF | Time(24, 0, 10)
TFX | Time(24, 10, 0)
FXX | Time(10, 0, 0)

Тестовите кои се поклопуваат ќе бидат земени во предвид само по еднаш.

### Тест случаи според критериумот  Every branch 

Почнувајќи со Time(24, 10, 10) се покриваат 
31, 33.1 -> 33.2 -> 33, 34, 35, 36 -> 37 -> 45 -> 55 -> 59 -> 63

Следно, со Time(10, 10, 10) дополнително се покриваат
45 -> 46 -> 49 -> 50 -> 33.3 -> 33.2 -> 62

На сличен начин се допокрива графот со следниве тестови:

Тест | Нови покриени
-----|--------------
Тime(-1, 10, 10) |  37 -> 38
Тime(26, 10, 10) |  38 -> 42
Тime(10, -1, 10) |  46 -> 47
Time(10, 10, -1) |  49 -> 52

Конечно со Test(24, 0, 0) се покриваат 55 -> 56 -> 33.3 со што сите врски се опфатени

### Објаснување на напишаните unit tests

Наведените Unit Tests во java фајлот се практично минималниот број потребни тестови со кои може да се исполнат двата критериуми.
Бидејќи кодот вклучува многу исклучоци, во every branch методот се итерира низ примерите и соодветните очекувани исклучоци.
Тестовите кои не очекуваме да фрлат исклучок ги тестираме со assertEquals, додека за оние од кои очекуваме исклучок користиме assertThrows. 
