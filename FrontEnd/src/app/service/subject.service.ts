import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "src/environments/environment";
import {Page} from "../interface/page";
import {Subject} from "../interface/subject";
import {Util} from "../util";


@Injectable()
export class SubjectService {
  private apiBaseUrl = environment.apiBaseUrl + '/subjects';

  constructor(private http: HttpClient) {
  }

  public readAll(page: number, pageSize: number, sortBy: string, order: string): Observable<Page<Subject>> {
    if (Util.isNullOrEmpty(sortBy, order)) {
      return this.http.get<Page<Subject>>(`${this.apiBaseUrl}?page=${page}&pageSize=${pageSize}`);
    }
    return this.http.get<Page<Subject>>(`${this.apiBaseUrl}?page=${page}&pageSize=${pageSize}&sort_by=${sortBy}&order=${order}`);
  }

}
