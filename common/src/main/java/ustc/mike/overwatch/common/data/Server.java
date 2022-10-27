/*******************************************************************************
 * Copyright © 2016 TangDongxin
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 ******************************************************************************/

package ustc.mike.overwatch.common.data;

/**
 * @author Mike
 * @project overwatch
 * @date 08/12/2017, 11:19 AM
 * @e-mail mike@mikecoder.cn
 */
public class Server extends Data {
    private long   id;
    private String ip;

    public long getId() {
        return id;
    }
    

    public void setId(long id) {
        this.id = id;
    }
    

    public String getIp() {
        return ip;
    }
    

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Server server = (Server) o;
    
        if (id != server.id) return false;
        return ip != null ? ip.equals(server.ip) : server.ip == null;
    }
    
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        return result;
    }
}
