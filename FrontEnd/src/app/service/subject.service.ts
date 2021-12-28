import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "src/environments/environment";

@Injectable()
export class SubjectService {
  private apiBaseUrl = environment.apiBaseUrl + '/subjects';

  constructor(private http: HttpClient) {
  }

  public readAll(page: number, pageSize: number, sortBy: string, order: string): Observable<any> {
    return this.http.get<any>(`${this.apiBaseUrl}?page=${page}&size=${pageSize}&sort_by=${sortBy}&order=${order}`);
  }

}