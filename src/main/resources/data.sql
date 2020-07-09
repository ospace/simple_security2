-- password: 1111
INSERT INTO `users`(`username`, `password`, `enabled`)
VALUES
('admin', '$2a$10$.qPMRoGUXR0vkDeWVBDZ2OGhcRW5rYDzulUjMMXKZZ.POxXViu7hi', TRUE),
('user', '$2a$10$.qPMRoGUXR0vkDeWVBDZ2OGhcRW5rYDzulUjMMXKZZ.POxXViu7hi', TRUE),
('master', '$2a$10$.qPMRoGUXR0vkDeWVBDZ2OGhcRW5rYDzulUjMMXKZZ.POxXViu7hi', TRUE);

INSERT INTO `authorities`(`username`, `authority`)
VALUES
('admin', 'ROLE_ADMIN'),
('user', 'ROLE_USER');

INSERT INTO `groups`(`group_name`)
VALUES ('administrator');

INSERT INTO `group_authorities`(`group_id`, `authority`) VALUES
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER');

INSERT INTO `group_members`(`id`, `username`, `group_id`)
VALUES (1, 'master', 1);

INSERT INTO acl_sid (id, principal, sid) VALUES
  (1, 1, 'admin'),
  (2, 1, 'user'),
  (3, 0, 'ROLE_EDITOR');

INSERT INTO acl_class (id, class) VALUES
  (1, 'com.tistory.ospace.simplesecurity2.entity.Notice');
 

INSERT INTO acl_object_identity
  (id, object_id_class, object_id_identity, 
  parent_object, owner_sid, entries_inheriting) 
VALUES
  (1, 1, 1, NULL, 1, 0),
  (2, 1, 2, NULL, 1, 0),
  (3, 1, 3, NULL, 1, 0);

-- https://docs.spring.io/spring-security/site/docs/3.0.x/reference/domain-acls.html
-- mask: permission
-- bit 0: read, bit 1: write, bit 2: create, bit 3: delete  
INSERT INTO acl_entry 
  (id, acl_object_identity, ace_order, sid, 
   mask, granting, audit_success, audit_failure) 
VALUES
  (1, 1, 1, 1,  1, 1, 1, 1),
  (2, 1, 2, 1,  2, 1, 1, 1),
  (3, 1, 3, 3,  1, 1, 1, 1),
  (4, 2, 1, 2,  1, 1, 1, 1),
  (5, 2, 2, 3,  1, 1, 1, 1),
  (6, 3, 1, 3,  1, 1, 1, 1),
  (7, 3, 2, 3,  2, 1, 1, 1),  
  
  (8, 2, 3, 1,  1, 1, 0, 1),
  (9, 3, 3, 1,  1, 1, 0, 1);
  
INSERT INTO notice_message(id,content) VALUES
  (1,'First Level Message'),
  (2,'Second Level Message'),
  (3,'Third Level Message');
