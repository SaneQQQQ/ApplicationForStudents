import {Group} from "./group";

export interface Student {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  group: Group;
  averageRank: number;
}
