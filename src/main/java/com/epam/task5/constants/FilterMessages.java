package com.epam.task5.constants;

public interface FilterMessages {
    String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    String INVALID_INPUT = "Некорректный ввод, выберите 1 (да) или 0 (нет), попробуйте еще раз:";
    String SEARCH_BY_NAME = "искать по имени файла? (0/1)";
    String INPUT_NAME = "Введите имя";
    String SEARCH_BY_EXTENSION = "искать по расширению файла? (0/1)";
    String INPUT_EXTENSION = "Введите расширение";
    String SEARCH_BY_SIZE = "искать по диапазону размеров файла? (0/1)";
    String INPUT_MIN_SIZE = "Введите минимальный размер файла (в байтах)";
    String INPUT_MAX_SIZE = "Введите максимальний размер файла (в байтах)";
    String SEARCH_BY_UPDATE_DATE = "искать по диапазону дат изменения файла? (0/1)";
    String INPUT_FROM_DATE = "Введите начало диапазона (дату изменения файла) [формат: " + DATE_TIME_FORMAT + "]";
    String INPUT_TO_DATE = "Введите конец диапазона (дату изменения файла) [формат: " + DATE_TIME_FORMAT + "]";
}
