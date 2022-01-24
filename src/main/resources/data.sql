Insert into users (username, password, enabled)
Values ('john','pass',true);

Insert into users (username, password, enabled)
Values ('peter','pass',true);

Insert into users (username, password, enabled)
Values ('rocky','pass',true);

Insert into authorities(username,authority)
Values ('john','ROLE_USER');

Insert into authorities(username,authority)
Values ('peter','ROLE_USER');