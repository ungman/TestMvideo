### Тестирование сайта 

Здесь находятся выполненые в учебных целях тесты.

Тестируется веб магазин, его возможности по добавлению товара в корзину.
* Добавление товара в корзину с помощью поиска
* Добавление товара в корзину с страницы поиска 
* Оформление товара с достакой 
* Оформление товаара без доставки

Все тесты находятся в пакетe io.github.ungman.tests

| class        | описание           | 
| :------------ |:-------------| 
| AddProductToCardFromSearchPage | Проверяется добавка в корзину с помошью поиска| 
| AddToCardFromPageProduct     | Проверяется добавка в корзину с страницы товара      |   
| BuyGoodsByGuestWithoutDelivery | Проверка оформления заказа без доставки     | 
| ByGoodsByGuestWithDelivery | Проверка оформления заказа с доставкой     | 
| CheckOrderCost | Проверка добавления товара в корзину на определенную сумму    | 
| SearchProduct | Проверка поиска товара    | 


Для запуска необходимо указать данные в файле src/main/resources/application.properties