import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "src/environments/environment";
import {Group} from "../interface/group";
import {Page} from "../interface/page";
import {Util} from "../util";

@Injectable()
export class GroupService {
  private apiBaseUrl = environment.apiBaseUrl + '/groups';

  constructor(private http: HttpClient) {
  }

  public create(group: Group): Observable<Group> {
    return this.http.post<Group>(`${this.apiBaseUrl}`, group);
  }

  public update(group: Group): Observable<Group> {
    return this.http.put<Group>(`${this.apiBaseUrl}`, group);
  }

  public delete(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiBaseUrl}/${id}`);
  }

  public read(id: number): Observable<Group> {
    return this.http.get<Group>(`${this.apiBaseUrl}/${id}`);
  }

  public readAll(page: number, pageSize: number, sortBy: string, order: string): Observable<Page<Group>> {
    if (Util.isNullOrEmpty(sortBy, order)) {
      return this.http.get<Page<Group>>(`${this.apiBaseUrl}?page=${page}&page_size=${pageSize}`);
    }
    return this.http.get<Page<Group>>(`${this.apiBaseUrl}?page=${page}&page_size=${pageSize}&sort_by=${sortBy}&order=${order}`);
  }
}
