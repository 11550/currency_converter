**Cервис для конвертации валют и сбора статистики операций**

перечень URI-адресов и готовые JSON для ручного тестирования

**1. Конвертация валют. Необходимо передать JSON**

**POST** http://localhost:8080/exchange/

**Body:**
{
    "userId" : "1",
    "amount" : "10000",
    "from" : "USD",
    "to" : "RUB"    
}

**Body:**
{
    "userId" : "2",
    "amount" : "101",
    "from" : "EUR",
    "to" : "RUB"    
}

**Body:**
{
    "userId" : "3",
    "amount" : "1000",
    "from" : "usd",
    "to" : "eur"    
}

**Body:**
{
    "userId" : "1",
    "amount" : "10",
    "from" : "USD",
    "to" : "RUB"    
}

**2. Получение пользователей, запросивших конвертацию в выбраной валюте больше за один раз, чем переданное значение.** 

Входные параметры: 

1. Сокращенная запись валюты;
2. Значение для запроса.
 
**GET** http://localhost:8080/stats/by_one_time?amount=10000

**GET** http://localhost:8080/stats/by_one_time?exchange_from=USD&amount=10000

**GET** http://localhost:8080/stats/by_one_time?exchange_from=EUR&amount=100

 **3. Получение пользователей, суммарный запрошенный объём которых больше, чем указанное число в выбранной валюте.**
 
 Входные параметры:
 
 1. Сокращенная запись валюты;
 2. Значение для запроса.
 
 **GET** http://localhost:8080/stats/by_all_times?amount=100000
 
 **GET** http://localhost:8080/stats/by_all_times?exchange_from=USD&amount=30000
 
 **GET** http://localhost:8080/stats/by_all_times?exchange_from=EUR&amount=30000
 
 **4. Рейтинг направлений конвертации валют по популярности.**
 
 **GET** http://localhost:8080/stats
 