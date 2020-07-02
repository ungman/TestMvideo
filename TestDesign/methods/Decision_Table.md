Таблица решений
Таблица решений  - бизнес - логика задокументирована как набор условий и действий
и представленна в виде таблицы

No, this is not possible with GitHub-Flavored Markdown. As the spec explains (emphasis added):

The remainder of the table’s rows may vary in the number of cells. If there are a number of cells fewer than the number of cells in the header row, empty cells are inserted. If there are greater, the excess is ignored:

Of course, you can always fall back to raw HTML. In fact, GitHub includes the rowspan (and colspan) attribute on their whitelist.

<table>
<thead>
  <tr>
    <th colspan="2"></th>
    <th>ТК1</th>
    <th>ТК2</th>
    <th>ТК3</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td rowspan="3">Условия</td>
    <td>роль</td>
    <td>гость</td>
    <td>администратор</td>
    <td>пользователь</td>
  </tr>
  <tr>
    <td>Локация</td>
    <td>UK</td>
    <td>US</td>
    <td>US</td>
  </tr>
  <tr>
    <td>Секция сайта</td>
    <td>admin</td>
    <td>admin</td>
    <td>user</td>
  </tr>
  <tr>
    <td rowspan="2">Действия</td>
    <td>Данная страница недостпупна с вашего региона</td>
    <td>-</td>
    <td>+</td>
    <td>-</td>
  </tr>
  <tr>
    <td>Вы не имеете достаточно прав для просмотра</td>
    <td>+</td>
    <td>-</td>
    <td>-</td>
  </tr>
  <tr>
    <td>Страница отображвется</td>
    <td>-</td>
    <td>-</td>
    <td>+</td>
  </tr>
</tbody>
</table>