import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, map, Observable} from "rxjs";
import {environment} from "src/environments/environment";
import {Page} from "../interface/page";
import {Subject} from "../interface/subject";
import {Util} from "../util";


@Injectable()
export class SubjectService {
  private apiBaseUrl = environment.apiBaseUrl + '/subjects';

  constructor(private http: HttpClient) {
  }

  public create(subject: Subject): Observable<Subject | unknown> {
    return this.http.post<Subject>(`${this.apiBaseUrl}`, subject).pipe(
      map((data: Subject) => {
        return data;
      }),
      catchError(err => {
        return err.error;
      }));
  }

  public readAll(page: number, pageSize: number, sortBy: string, order: string): Observable<Page<Subject>> {
    if (Util.isNullOrEmpty(sortBy, order)) {
      return this.http.get<Page<Subject>>(`${this.apiBaseUrl}?page=${page}&page_size=${pageSize}`);
    }
    return this.http.get<Page<Subject>>(`${this.apiBaseUrl}?page=${page}&page_size=${pageSize}&sort_by=${sortBy}&order=${order}`);
  }

}
