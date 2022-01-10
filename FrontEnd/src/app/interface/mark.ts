import {Student} from "./student";
import {Subject} from "./subject";

export interface Mark {
  student: Student;
  subject: Subject;
  mark: number;
}
