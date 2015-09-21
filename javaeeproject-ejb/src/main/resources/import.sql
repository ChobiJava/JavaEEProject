DROP TABLE IF EXISTS `contact_info`;
CREATE TABLE `contact_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `adress` VARCHAR(45) NOT NULL,
  `zip` INT(5) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` VARCHAR(45) not null,
  `ssn` VARCHAR(12) NOT NULL UNIQUE,
  `contact_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `student_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contact_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_type` VARCHAR(45) not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `user_id` int(11) not null,
  `contact_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `teacher_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `teacher_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contact_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`course_name` varchar(20) NOT NULL,
`points` int(11) NOT NULL,
`startDate` DATE,
`endDate` DATE,
`teacher_id` INT(11) NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `teacher_course_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `red_day`;
CREATE TABLE `red_day` (
  `id` int(11) NOT NULL,
  `redDay` DATE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`student_id` int(11) NOT NULL,
`course_id` int(11) NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `student_student_course_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
CONSTRAINT `course_student_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `date` DATE not null,
  PRIMARY KEY (`id`),
  CONSTRAINT `student_attendance_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `course_attendance_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;





