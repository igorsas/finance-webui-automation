delete
from deposit.deposit;
ALTER TABLE deposit.deposit
    AUTO_INCREMENT = 1;
delete
from deposit.term;
ALTER TABLE deposit.term
    AUTO_INCREMENT = 1;
delete
from deposit.adding_type;
ALTER TABLE deposit.adding_type
    AUTO_INCREMENT = 1;
delete
from deposit.currency;
ALTER TABLE deposit.currency
    AUTO_INCREMENT = 1;

insert into deposit.currency(code)
values ('UAH'),
       ('USD'),
       ('EUR');

insert into deposit.adding_type(type)
values ('monthly'),
       ('quarterly'),
       ('yearly'),
       ('none');

insert into deposit.term(start_date, period, period_type)
values (DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 5, 'months'),
       (DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 2, 'years');

insert into deposit.deposit(initial_sum, interest_rate, currency_id, term_id,
                            replenishment_type_id, replenishment_sum, capitalization_type_id)
values (1000, 5.5, 1, 1, 1, 100, 1),
       (1000, 5.5, 1, 1, 1, 100, 2),
       (1000, 2.7, 1, 2, 1, 100, 3),
       (1000, 8.8, 1, 1, 1, 100, 4),
       (1000, 1.4, 1, 1, 2, 100, 1),
       (1000, 4.2, 1, 1, 3, 100, 1),
       (1000, 2.1, 1, 1, 4, 0, 1),
       (1000, 4.5, 1, 1, 2, 100, 2),
       (1000, 2.7, 1, 2, 2, 100, 3),
       (1000, 11.7, 1, 1, 2, 100, 4),
       (1000, 11.7, 1, 2, 3, 100, 2),
       (1000, 10.5, 1, 1, 4, 0, 2),
       (1000, 2.7, 1, 2, 3, 100, 3),
       (1000, 8.8, 1, 2, 3, 100, 4),
       (1000, 9.9, 1, 1, 4, 0, 4),
       (1000, 4.5, 2, 1, 4, 0, 1),
       (1000, 2.1, 3, 1, 4, 0, 1);