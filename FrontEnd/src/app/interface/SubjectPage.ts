import {Subject} from "./subject";

export interface SubjectPage {
  content: Subject[];
  totalElements: number;
  number: number;
  size: number;
  numberOfElements: number;
}
