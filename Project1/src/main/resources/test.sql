INSERT INTO users (user_id, first_name, last_name, username, password, role)
VALUES
    (1, 'Cyrus', 'De Jesus', 'cdejesus', 'password', 'Manager'),
    (2, 'Us', 'Er', 'user', 'user', 'User'),
    (3, 'Emp', 'Loyee', 'employee', 'employee', 'Employee'),
    (4, 'Ben', 'Petruzziello', 'bpetruzziello', 'password', 'Employee'),
    (5, 'Donald', 'Trump', 'dtrump', 'dtrump', 'Employee'),
    (6, 'Kamala', 'Harris', 'kharris', 'kharris', 'Employee');

INSERT INTO reimbursements (reimbursement_id, user_id, amount, description, status)
VALUES
    (1, 1, 1000, 'Relocating to Raleigh, NC', 'Approved'),
    (2, 1, 200, 'Certifications for Python', 'Pending'),
    (3, 3, 300, 'Changing my entire name due to workplace harassment', 'Pending'),
    (4, 4, 4000, 'Travel expenses to North Korea', 'Approved'),
    (5, 5, 5, 'Taxes', 'Pending'),
    (6, 6, 20000000, 'Campaigns', 'Denied');



