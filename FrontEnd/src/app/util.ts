export class Util {
  public static isNullOrEmpty(sortBy: string, order: string): boolean {
    return sortBy == null || order == "";
  }
}
